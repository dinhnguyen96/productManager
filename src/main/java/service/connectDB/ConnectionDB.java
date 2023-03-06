package service.connectDB;

import java.sql.*;

public class ConnectionDB
{
    private static final String URL = "jdbc:mysql://localhost:3306/product";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Nguyen251096*";

    public static Connection getConnection()
    {
        Connection connection;
        try
        {
            DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        }
        catch (SQLException e)
        {
            System.out.println("Lỗi kết nối dữ liệu");
        }
        return null;
    }
    public static ResultSet selectQuerry(String sql)
    {
        Connection cnn = getConnection();
        ResultSet resultSet = null;
        if (cnn != null)
        {
            try
            {
                PreparedStatement p = cnn.prepareStatement(sql);
                resultSet = p.executeQuery();
            }
            catch (SQLException e)
            {
                System.out.println("Lỗi sai cú pháp truy vấn");
            }

        }
        return resultSet;

    }
}
