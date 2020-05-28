package de.neuefische.orderspringserver.controller;

import de.neuefische.orderspringserver.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired //Durch Autowired gibt uns Spring direkt eine "Instanz" vom RestTemplate (s. Zeile 15) ..
    // TestRestTemplate = new TestrestTemplate etc.. nicht nötig!
    private TestRestTemplate restTemplate;

    @Test
    public void ProductsLengthShouldBeEight(){
        //GIVEN

        //WHEN
        ResponseEntity<Product[]> response = restTemplate.getForEntity("http://localhost:" + port + "/products", Product[].class);
        HttpStatus statusCode = response.getStatusCode();
        Product[] products = response.getBody();

        //THEN
        assertEquals(HttpStatus.OK, statusCode);
        assertEquals(8, products.length);
    }

    @Test
    public void listProductsShouldReturnAllProducts(){
        //GIVEN
        List<Product> testList =  List.of(new Product("Cheese", "1"),new Product("Pizza",  "2"),new Product("Pasta",  "3"),new Product("Carrot", "4"),new Product("Pepper", "5"),new Product("Broccoli", "6"),new Product("Spinaci", "7"),new Product("Apple", "8"  ));
        //WHEN
        ResponseEntity<Product[]> response = restTemplate.getForEntity("http://localhost:" + port + "/products", Product[].class);
        HttpStatus statusCode = response.getStatusCode();
        Product[] products = response.getBody();

        //THEN
        assertEquals(HttpStatus.OK, statusCode);
        assertArrayEquals(testList.toArray(), products);

    }

}