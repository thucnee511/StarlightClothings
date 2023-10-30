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
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import starlight.entity.DBContext;

/**
 *
 * @author Admin
 */
public class CartDAO {

    private static final String GET_CART
            = "SELECT [UserID],[ProductID],[Quantity] FROM [dbo].[Cart] "
            + "WHERE [UserID] = ";
    private static final String REMOVE_CART
            = "DELETE FROM [dbo].[Cart]"
            + "WHERE [UserID] = ?";
    private static final String UPDATE_CART
            = "INSERT INTO [dbo].[Cart]"
            + "([UserID],[ProductID],[Quantity])"
            + " VALUES (? , ? , ?)";
    private static final String UPDATE_CART_PRODUCT
            = "UPDATE [dbo].[Cart]"
            + " SET [Quantity] = ?"
            + " WHERE [UserID] = ? AND [ProductID] = ?";
    private static final String DELETE_CART_PRODUCT
            = "DELETE FROM [dbo].[Cart]"
            + "WHERE [UserID] = ? AND [ProductID] = ?";
    private static final String CHECKOUT
            = "INSERT INTO [dbo].[Orders] ([OrderID],[UserID],[Total])"
            + "     VALUES (?,?,?)";
    private static final String ADD_ORDER_DETAIL
            = "INSERT INTO [dbo].[OrdersDetails]([OrderID],[ProductID],[Quantity],[Total])"
            + "     VALUES (?,?,?,?)";

    public CartDTO getCart(String userID)
            throws ClassNotFoundException, SQLException {
        Connection con;
        PreparedStatement stm;
        ResultSet rs;

        con = DBContext.getConnection();
        String query = GET_CART + "'" + userID + "'";
        stm = con.prepareStatement(query);
//        stm.setString(1, userID);
        rs = stm.executeQuery();
        CartDTO c = new CartDTO();
        while (rs.next()) {
            String productID = rs.getString("ProductID");
            int quantity = rs.getInt("Quantity");
            ProductDTO p = new ProductDTO(productID, quantity);
            c.add(p);
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

        return c;
    }

    public boolean removeCart(String userID)
            throws ClassNotFoundException, SQLException {
        Connection con;
        PreparedStatement stm;

        con = DBContext.getConnection();
        stm = con.prepareStatement(REMOVE_CART);
        stm.setString(1, userID);
        stm.executeUpdate();

        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return true;
    }

    public boolean updateCart(String userID, CartDTO cart)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        con = DBContext.getConnection();

        for (ProductDTO p : cart.getCart()) {
            stm = con.prepareStatement(UPDATE_CART);
            stm.setString(1, userID);
            stm.setString(2, p.getProductID());
            stm.setInt(3, p.getQuantity());
            stm.executeUpdate();
        }

        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return true;
    }

    public boolean updateCart(String userID, String productID, String quantity)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        con = DBContext.getConnection();
        stm = con.prepareStatement(UPDATE_CART_PRODUCT);
        stm.setString(1, quantity);
        stm.setString(2, userID);
        stm.setString(3, productID);
        stm.executeUpdate();

        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return true;
    }

    public boolean deleteCart(String userID, String productID)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        con = DBContext.getConnection();
        stm = con.prepareStatement(DELETE_CART_PRODUCT);
        stm.setString(1, userID);
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

    public boolean addOrder(String orderID, String productID, int quantity, int total)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        con = DBContext.getConnection();
        stm = con.prepareStatement(ADD_ORDER_DETAIL);
        stm.setString(1, orderID);
        stm.setString(2, productID);
        stm.setInt(3, quantity);
        stm.setInt(4, total);
        stm.executeUpdate();

        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return true;
    }

    public String addOrder(String userID, String total)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        con = DBContext.getConnection();
        stm = con.prepareStatement(CHECKOUT);
        String orderID = generateOrderID();
        stm.setString(1, orderID);
        stm.setString(2, userID);
        stm.setString(3, total);
        stm.executeUpdate();

        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }

        return orderID;
    }

    private String generateOrderID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static void main(String[] args) {
        try {
            CartDAO dao = new CartDAO();
            dao.removeCart("");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
