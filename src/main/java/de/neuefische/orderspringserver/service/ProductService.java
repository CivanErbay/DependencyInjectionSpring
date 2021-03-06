package de.neuefische.orderspringserver.service;

import de.neuefische.orderspringserver.data.ProductDb;
import de.neuefische.orderspringserver.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service        //Dependency Injection
public class ProductService {

    //Attribut
    private ProductDb productDb;

    //Konstruktor
    @Autowired
    public ProductService(ProductDb productDb) {
        this.productDb = productDb;
    }

    //Methoden
    public List<Product> getProducts () {
        return productDb.listProducts();
    }

    public Product getProduct(String name) {
        return productDb.showProduct(name);
    }

    public Product getProductById(String id) {
        return productDb.getProductById(id);
    }


}
