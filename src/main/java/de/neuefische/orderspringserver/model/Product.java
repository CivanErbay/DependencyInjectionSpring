package de.neuefische.orderspringserver.model;

import de.neuefische.orderspringserver.data.ProductDb;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
public class Product {


    //Attribut
    private String  name;
    private String id;

    //Konstruktor

    public Product(String name, String id){
        this.name = name;
        this.id = id;
    }




}
