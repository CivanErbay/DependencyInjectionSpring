package de.neuefische.orderspringserver.service;

import de.neuefische.orderspringserver.data.OrderDb;
import de.neuefische.orderspringserver.data.ProductDb;
import de.neuefische.orderspringserver.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderService {

    private OrderDb orderDb;
    private ProductDb productDb;

    public OrderService(OrderDb orderDb, ProductDb productDb) {
        this.orderDb = orderDb;
        this.productDb = productDb;
    }

    //Methoden
    public OrderDb listOrder () {
    return orderDb;
    }

    public void addOrder(Order order) {
        //Check if Product is in ProductDB
        if (productDb.checkOrder(order)) {
            //If avaiable add product
            orderDb.addOrder(order);
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    public Order getOrderById(String id){
       return orderDb.getOrderById(id);
    }



}
