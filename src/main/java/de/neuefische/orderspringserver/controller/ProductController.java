package de.neuefische.orderspringserver.controller;

import de.neuefische.orderspringserver.data.ProductDb;
import de.neuefische.orderspringserver.model.Product;
import de.neuefische.orderspringserver.service.ProductService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("products")
public class ProductController {

    //Attribut
    ProductService productService;

    //Konstruktor
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping//static Search Path
    public ProductDb listProducts() {
        return productService.getProductDb();
    }

    //METHODE 1 - StartsWith
    @GetMapping("{searchProduct}")
    public Product showProduct(@PathVariable() String searchProduct ) {
        return productService.getProduct(searchProduct);
    }

    //STATISCH nach ID
    @GetMapping("find")
    public Product getProductById(@RequestParam(name ="id",required = false) String id){
        return productService.getProductById(id);
    }

}
