package de.neuefische.orderspringserver.data;

import de.neuefische.orderspringserver.model.Order;
import de.neuefische.orderspringserver.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Repository //Dependency Injection
public class ProductDb {
    //Attribut
    private List<Product> products = List.of(new Product("Cheese", "1"),new Product("Pizza",  "2"),new Product("Pasta",  "3"),new Product("Carrot", "4"),new Product("Pepper", "5"),new Product("Broccoli", "6"),new Product("Spinaci", "7"),new Product("Apple", "8"  ));

    //falscher Konstruktor, da er die obige Liste überschreibt
    public ProductDb(List<Product> products) {
        /*this.products = products;*/
    }

    //Methoden..
    public List<Product> listProducts () {
        return products;
    }


    public Product showProduct(String name) {
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getName().toLowerCase().startsWith(name.toLowerCase())) {
                return products.get(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    
    public Product getProductById(String id) {
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId().equals(id)) {
                return products.get(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public boolean checkProducts(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkOrder(Order order) {
        for (int i = 0; i < order.getProducts().size(); i++) {
            if(!checkProducts(order.getProducts().get(i).getId())) {
            return false;
            }
        }
        return true;
    }




}
