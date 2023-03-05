package controller;

import model.Producer;
import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

import java.io.*;
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
        switch (action) {
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
                Product product = productService.get(id);
                request.setAttribute("id", id);
                request.setAttribute("productCode",product.getProductCode());
                request.setAttribute("productName", product.getProductName());
                request.setAttribute("productPrice", product.getProductPrice());
                request.setAttribute("productcer", product.getProducer().getProducerName());
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
        Product product = productService.get(id);
        productService.remove(product);
        response.sendRedirect("http://localhost:8080/product");
    }
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Product> productList = productService.findAll();
        String productCode = request.getParameter("productCode");
        String productName = request.getParameter("productName");
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));
        String producerName = request.getParameter("productcer");
        String note = request.getParameter("note");
        Producer producer = new Producer(3, producerName);
        Product product = new Product(productList.get(productList.size()-1).getId()+1,productCode,productName,
                productPrice, producer, note);
        boolean productAdd = productService.add(product);

        if (productAdd)
        {
            response.sendRedirect("http://localhost:8080/product");
        }
        else
        {
            request.setAttribute("productCode",productCode );
            request.setAttribute("productName", producerName);
            request.setAttribute("productPrice", productPrice);
            request.setAttribute("productcer", producer.getProducerName());
            request.setAttribute("action","add");
            request.getRequestDispatcher("update_insert.jsp").forward(request,response);
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
        String producerName = request.getParameter("productcer");
        String note = request.getParameter("note");
        Product product = productService.get(id);
        Producer producer = product.getProducer();
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setProductCode(productCode);
        product.setNote(note);
        producer.setProducerName(producerName);
        product.setProducer(producer);
        productService.update(product);
        response.sendRedirect("http://localhost:8080/product");

    }

}