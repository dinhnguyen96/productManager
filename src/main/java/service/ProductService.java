package service;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {


    List<Product> findAll();

    Product get(long id) throws SQLException;

    int add(Product product);

    int update(Product product);

    int remove(long id);
}
