package service;

import model.Product;

import java.util.List;

public interface ProductService {


    List<Product> findAll();

    Product get(long id);

    boolean add(Product product);

    void update(Product product);

    void remove(Product product);
}
