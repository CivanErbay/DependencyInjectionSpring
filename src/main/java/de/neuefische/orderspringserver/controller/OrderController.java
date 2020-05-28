package de.neuefische.orderspringserver.controller;

import de.neuefische.orderspringserver.data.OrderDb;
import de.neuefische.orderspringserver.data.ProductDb;
import de.neuefische.orderspringserver.model.Order;
import de.neuefische.orderspringserver.model.Product;
import de.neuefische.orderspringserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    //Attribut
    OrderService orderService;
    @Autowired
    //Konstruktor
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

  @GetMapping("orderlist")
  public OrderDb listOrders() {
      return orderService.listOrder();
  }

  //Pathvariable Searchfield
    @GetMapping("{searchOrder}")
    public Order getOrderById(@PathVariable() String searchOrder) {
        return orderService.getOrderById(searchOrder);
  }

  @PutMapping("addOrder")
    public void addOrder(@RequestBody Order order) {
       orderService.addOrder(order);
  }
    //addOrder JSON Format für PostMAN
   /* {
        "id":"1",
            "products" : [
        {
            "name" : "pizza",
                "id"   : "1"
        }
		]
    }*/

}
