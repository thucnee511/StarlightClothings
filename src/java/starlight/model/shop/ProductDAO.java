/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starlight.model.shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import starlight.entity.DBContext;

/**
 *
 * @author Admin
 */
public class ProductDAO {

    private static final String GET_ALL_PRODUCTS
            = "SELECT [ProductID],[CateID],[ProductName],[Image],[Price],[Quantity]"
            + " FROM [dbo].[Products]";
    private static final String GET_PRODUCTS_BY_CATE
            = "SELECT [ProductID],[CateID],[ProductName],[Image],[Price],[Quantity]"
            + " FROM [dbo].[Products] WHERE [CateID] = ?";
    private static final String SEARCH_PRODUCT
            = "SELECT [ProductID],[CateID],[ProductName],[Image],[Price],[Quantity]"
            + " FROM [dbo].[Products] WHERE [CateID] = ? AND [ProductName] LIKE ?";
    private static final String SEARCH_PRODUCT_V2
            = "SELECT [ProductID],[CateID],[ProductName],[Image],[Price],[Quantity]"
            + " FROM [dbo].[Products] WHERE [ProductName] LIKE ?";
    private static final String GET_PRODUCT
            = "SELECT [ProductID],[CateID],[ProductName],[Image],[Price],[Quantity]"
            + " FROM [dbo].[Products] WHERE [ProductID] = ?";
    private static final String UPDATE_PRODUCT_QUANTITY
            = "UPDATE [dbo].[Products]"
            + "   SET [Quantity] = ?"
            + " WHERE [ProductID] = ?";

    public ProductDTO getroduct(String productID)
            throws ClassNotFoundException, SQLException {
        Connection con;
        PreparedStatement stm;
        ResultSet rs;

        con = DBContext.getConnection();
        stm = con.prepareStatement(GET_PRODUCT);
        stm.setString(1, productID);
        rs = stm.executeQuery();
        ProductDTO p = null;
        if (rs.next()) {
            String cateID = rs.getString("CateID");
            String name = rs.getString("ProductName");
            String image = rs.getString("Image");
            int price = rs.getInt("Price");
            int quantity = rs.getInt("Quantity");
            p = new ProductDTO(productID, cateID, name, image, price, quantity);
        }

        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return p;
    }

    public List<ProductDTO> getAllProducts()
            throws ClassNotFoundException, SQLException {
        Connection con;
        PreparedStatement stm;
        ResultSet rs;

        List<ProductDTO> list = new ArrayList<>();
        con = DBContext.getConnection();
        stm = con.prepareStatement(GET_ALL_PRODUCTS);
        rs = stm.executeQuery();
        while (rs.next()) {
            String productID = rs.getString("ProductID");
            String cateID = rs.getString("CateID");
            String name = rs.getString("ProductName");
            String image = rs.getString("Image");
            int price = rs.getInt("Price");
            int quantity = rs.getInt("Quantity");
            ProductDTO p = new ProductDTO(productID, cateID, name, image, price, quantity);
            list.add(p);
        }

        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return list;
    }

    public List<ProductDTO> getProducts(String cateID)
            throws ClassNotFoundException, SQLException {
        Connection con;
        PreparedStatement stm;
        ResultSet rs;

        List<ProductDTO> list = new ArrayList<>();
        con = DBContext.getConnection();
        stm = con.prepareStatement(GET_PRODUCTS_BY_CATE);
        stm.setString(1, cateID);
        rs = stm.executeQuery();
        while (rs.next()) {
            String productID = rs.getString("ProductID");
            String name = rs.getString("ProductName");
            String image = rs.getString("Image");
            int price = rs.getInt("Price");
            int quantity = rs.getInt("Quantity");
            ProductDTO p = new ProductDTO(productID, cateID, name, image, price, quantity);
            list.add(p);
        }

        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return list;
    }

    public boolean updateProductQuantity(String productID, int quantity)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        con = DBContext.getConnection();
        stm = con.prepareStatement(UPDATE_PRODUCT_QUANTITY);
        stm.setInt(1, quantity);
        stm.setString(2, productID);
        stm.executeUpdate();

        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return true;
    }

    public List<ProductDTO> searchProduct(String keyword, String cateID)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        List<ProductDTO> list = new ArrayList<>();
        con = DBContext.getConnection();
        if (cateID.equals("0")) {
            stm = con.prepareStatement(SEARCH_PRODUCT_V2);
            stm.setString(1, "%" + keyword + "%");
        } else {
            stm = con.prepareStatement(SEARCH_PRODUCT);
            stm.setString(1, cateID);
            stm.setString(2, "%" + keyword + "%");
        }
        rs = stm.executeQuery();
        while (rs.next()) {
            String productID = rs.getString("ProductID");
            String productName = rs.getString("ProductName");
            String image = rs.getString("Image");
            String _cateID = rs.getString("CateID");
            int price = rs.getInt("Price");
            int quantity = rs.getInt("Quantity");
            ProductDTO p = new ProductDTO(productID, _cateID, productName, image, price, quantity);
            list.add(p);
        }

        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return list;
    }
}
