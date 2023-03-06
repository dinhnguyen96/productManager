package controller;

import model.Manufacture;
import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "productServlet", value = "/product")
public class ProductServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null)
        {
            action = "";
        }
        switch (action)
        {
            case "add" ->
            {
                add(req, resp);
            }
            case "edit" -> {
                edit(req, resp);
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String action = request.getParameter("action");
        if (action == null)
        {
            action = "";
        }
        switch (action)
        {
            case "add" -> {
                request.setAttribute("action",action);
                request.getRequestDispatcher("update_insert.jsp").forward(request, response);
            }
            case "edit", "detail" ->{
                long id = Long.parseLong(request.getParameter("id"));
                Product product;
                try {
                    product = productService.get(id);
                    request.setAttribute("id", id);
                    request.setAttribute("productCode",product.getProductCode());
                    request.setAttribute("productName", product.getProductName());
                    request.setAttribute("productPrice", product.getProductPrice());
                    request.setAttribute("manufacturer_id", product.getManufacture_id());
                    request.setAttribute("note", product.getNote());
                    if (action.equalsIgnoreCase("edit"))
                    {
                        request.setAttribute("action",action);
                        request.getRequestDispatcher("update_insert.jsp").forward(request, response);
                    }
                    else
                    {
                        request.getRequestDispatcher("detail.jsp").forward(request, response);
                    }

                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
            case "remove" -> {
                remove(request,response);
            }
            default -> {
                request.setAttribute("products", productService.findAll());
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }
    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        long id = Long.parseLong(request.getParameter("id"));
        productService.remove(id);
        response.sendRedirect("http://localhost:8080/product");

    }
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String productCode = request.getParameter("productCode");
        String productName = request.getParameter("productName");
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));
        long manufacturer_id = Long.parseLong(request.getParameter("manufacturer_id"));
        String note = request.getParameter("note");
        Product product = new Product(productCode,productName, productPrice, manufacturer_id, note);

        int result = productService.add(product);

        if (result == 0)
        {
            request.setAttribute("productCode",productCode );
            request.setAttribute("productName", productName);
            request.setAttribute("productPrice", productPrice);
            request.setAttribute("manufacturer_id", manufacturer_id);
            request.setAttribute("action","add");
            request.setAttribute("note",note);
            request.getRequestDispatcher("update_insert.jsp").forward(request,response);
        }
        else
        {
            response.sendRedirect("http://localhost:8080/product");
        }
    }
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        long id = Long.parseLong(request.getParameter("id"));
        String productCode = request.getParameter("productCode");
        String productName = request.getParameter("productName");

        String[] valueSplit = request.getParameter("productPrice").split(",");
        String result = "";
        for (String s : valueSplit) {
            result += s;
        }
        double productPrice = Double.parseDouble(result);
        long manufacturer_id = Long.parseLong(request.getParameter("manufacturer_id"));
        String note = request.getParameter("note");
        try {
            Product product = productService.get(id);
            product.setProductCode(productCode);
            product.setProductName(productName);
            product.setProductPrice(productPrice);
            product.setNote(note);
            product.setManufacture_id(manufacturer_id);
            productService.update(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("http://localhost:8080/product");

    }

}