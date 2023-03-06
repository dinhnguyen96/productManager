package service.impl;

import model.Product;
import service.ProductService;
import service.connectDB.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {


    @Override
    public List<Product> findAll()
    {
        String sql = "select * from product";

        List<Product> productList = new ArrayList<>();

        ResultSet rs = ConnectionDB.selectQuerry(sql);

        if (rs != null)
        {
           try
           {
              while (rs.next())
              {
                  long id = rs.getLong("id");
                  String code = rs.getString("code");
                  String name = rs.getString("name");
                  double price = rs.getDouble("price");
                  long manufacture_id = rs.getLong("manufacturer_id");
                  String note = rs.getString("note");
                  Product product = new Product(id,code,name,price,manufacture_id,note);
                  productList.add(product);
              }

           }
           catch (SQLException e)
           {
               System.out.println("Lỗi thực thi truy vấn");
           }
           finally
           {
               if (ConnectionDB.getConnection() != null)
               {
                   try {
                       ConnectionDB.getConnection().close();
                   } catch (SQLException e) {
                       throw new RuntimeException(e);
                   }
               }
           }
        }
        return productList ;
    }

    @Override
    public Product get(long id)
    {
        Connection cnn = null;
        try {
            String sql = "select * from product where id = ?";
            cnn = ConnectionDB.getConnection();
            if (cnn != null)
            {
                PreparedStatement p = cnn.prepareStatement(sql);
                p.setLong(1, id);
                ResultSet rs = p.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String code = rs.getString("code");
                    double price = rs.getDouble("price");
                    long manufacturer_id = rs.getLong("manufacturer_id");
                    String note = rs.getString("note");
                    Product product = new Product(id, code, name,price, manufacturer_id, note);
                    return product;
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Lỗi thực thi truy vấn");
        }
        finally
        {
            try
            {
                cnn.close();
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
        return null;

    }

    @Override
    public int add(Product product)
    {
        int result = 0;
        Connection cnn = null;
        try {
            String sql = "insert into product(code,name,price,manufacturer_id,note) values(?,?,?,?,?)";
            cnn = ConnectionDB.getConnection();
            if (cnn != null)
            {
                PreparedStatement p = cnn.prepareStatement(sql);
                p.setString(1,product.getProductCode());
                p.setString(2,product.getProductName());
                p.setDouble(3,product.getProductPrice());
                p.setLong(4, product.getManufacture_id());
                p.setString(5,product.getNote());
                result = p.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            System.out.println("Lỗi thực thi truy vấn");
        }
        finally
        {
            try
            {
                cnn.close();
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
    @Override
    public int update(Product product)
    {
        int result = 0;
        Connection cnn = null;
        try {
            String sql = "update product set code = ?, name = ? , price = ?, manufacturer_id = ?, note = ? where id = ?";
            cnn = ConnectionDB.getConnection();
            if (cnn != null)
            {
                PreparedStatement p = cnn.prepareStatement(sql);
                p.setString(1,product.getProductCode());
                p.setString(2,product.getProductName());
                p.setDouble(3,product.getProductPrice());
                p.setLong(4, product.getManufacture_id());
                p.setString(5,product.getNote());
                p.setLong(6, product.getId());
                result = p.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            System.out.println("Lỗi thực thi truy vấn");
        }
        finally
        {
            try
            {
                cnn.close();
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
        return result;

    }

    @Override
    public int remove(long id)
    {
        int result = 0;
        Connection cnn = null;
        try {
            String sql = "delete from product where id = ?";
            cnn = ConnectionDB.getConnection();
            if (cnn != null)
            {
                PreparedStatement p = cnn.prepareStatement(sql);
                p.setLong(1, id);
                result = p.executeUpdate();
            }
        }
        catch (SQLException e) {
            System.out.println("Lỗi thực thi truy vấn");
        }
        finally {
            try
            {
                if (cnn != null)
                {
                    cnn.close();
                }
            }
            catch (SQLException e)
            {
                System.out.println("Lỗi đóng dữ liệu");
            }
        }
        return result;
    }
}
