/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starlight.model.shop;

public class ProductDTO {

    private String productID;
    private String cateID;
    private String name;
    private String image;
    private int price;
    private int quantity;

    public ProductDTO() {
    }

    public ProductDTO(String productID, String cateID, String name, String image, int price, int quantity) {
        this.productID = productID;
        this.cateID = cateID;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductDTO(String productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "productID=" + productID + ", cateID=" + cateID + ", name=" + name + ", image=" + image + ", price=" + price + ", quantity=" + quantity + '}';
    }
}
