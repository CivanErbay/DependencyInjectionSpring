package de.neuefische.orderspringserver.controller;

import de.neuefische.orderspringserver.data.OrderDb;
import de.neuefische.orderspringserver.model.Order;
import de.neuefische.orderspringserver.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    OrderDb orderDb;

    @BeforeEach //wird vor jedem einzelnen Test ausgeführt!
    public void AddOrder() {
        List<Product> orderlist = List.of( new Product("Pizza", "2"));
        orderDb.addOrder(new Order("1", orderlist));
    }

    @Test //TESTET OB ORDER IN @BeforeEach erfolgreich geaddet wurde UND ob GET Methode orderlist funktioniert
    public void CheckOrderList() {
        //POST  -- ADD PROCESS

      Order ordertest = new Order("1", List.of(new Product("Cheese", "1"), new Product("Pizza", "2")));
     /*   ResponseEntity<Order> postResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/order/addOrder", new Order("1", orderlist), Order.class);

        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        assertEquals(new Order("1",orderlist), postResponse.getBody());
*/
        //WHEN -- GET PROCESS
        ResponseEntity<Order[]> response = restTemplate.getForEntity("http://localhost:" + port + "/order/orderlist", Order[].class);
        HttpStatus statusCode = response.getStatusCode();
        Order[] orders = response.getBody();

        //THEN
        assertEquals(HttpStatus.OK, statusCode);
        assertEquals(ordertest, orders[0]);

    }

    @Test // NOT WORKING ___ FIX!
    public void GetOrderByIdCheck() {

        //GIVEN
        Order testOrder = new Order("1",List.of(new Product("Pizza", "2")));

        //WHEN - GET PROCESS
        ResponseEntity<Order> response = restTemplate.getForEntity("http://localhost:" + port + "/order/1", Order.class);
        HttpStatus statusCode = response.getStatusCode();
        Order order = response.getBody();

        //THEN
        assertEquals(HttpStatus.OK, statusCode);
        assertEquals(testOrder, order);

    }
}