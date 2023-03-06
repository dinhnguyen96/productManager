package model;

public class Product {

    private long id;

    private String productCode;

    private String productName;

    private double productPrice;

    private long manufacture_id;

    private String note;

    public Product(long id, String productCode, String productName, double productPrice, long manufacture_id, String note) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.manufacture_id = manufacture_id;
        this.note = note;
    }
    public Product(String productCode, String productName, double productPrice, long manufacture_id, String note) {
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.manufacture_id = manufacture_id;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public long getManufacture_id() {
        return manufacture_id;
    }

    public void setManufacture_id(long manufacture_id) {
        this.manufacture_id = manufacture_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
