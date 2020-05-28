package de.neuefische.orderspringserver.data;

import de.neuefische.orderspringserver.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Data
@Repository
@AllArgsConstructor
public class OrderDb {

    private ArrayList<Order> orderList = new ArrayList<>();

    public ArrayList<Order> getOrderList(){
        return orderList;
    }

    public Order getOrderById(String id) {
        for (int i = 0; i < orderList.size(); i++) {
            if(orderList.get(i).getId().equals(id)) {
                return orderList.get(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void addOrder(Order order){
        orderList.add(order);
    }


}
