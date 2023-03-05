package service.impl;

import model.Producer;
import model.Product;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static List<Product> productList;


    static {
        productList = new ArrayList<>();
        productList.add(new Product(1L, "pd01","iPhone 13 256GB",
        19990000,new Producer(1L, "Apple"), "iphone"));
        productList.add(new Product(2L, "pd02","Samsung Galaxy S22 Ultra 5G 128GB ",
                25990000,new Producer(2L, "Samsung"), "Samsung"));
        productList.add(new Product(3L, "pd03","Samsung Galaxy S20 FE ",
                9490000,new Producer(2L, "Samsung"), "Samsung"));
        productList.add(new Product(4L, "pd04","OPPO Reno8 Pro 5G",
                17990000,new Producer(3L, "OPPO"), "Oppo"));
    }
    @Override
    public List<Product> findAll()
    {
        return productList;
    }

    @Override
    public Product get(long id)
    {
        for (Product product : productList)
        {
            if (product.getId() == id)
            {
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean add(Product product)
    {
        for (Product productCheck : productList)
        {
            if (productCheck.getProductCode().equalsIgnoreCase(product.getProductCode()))
            {
                return false;
            }
        }
        productList.add(product);
        return true;
    }
    @Override
    public void update(Product product)
    {
       productList.set(productList.indexOf(product),product);

    }

    @Override
    public void remove(Product product)
    {
        productList.remove(product);
    }
}
