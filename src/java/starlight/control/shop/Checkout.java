/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starlight.control.shop;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import starlight.model.shop.CartDAO;
import starlight.model.shop.CartDTO;
import starlight.model.shop.ProductDAO;
import starlight.model.shop.ProductDTO;
import starlight.model.user.UserDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Checkout", urlPatterns = {"/checkout"})
public class Checkout extends HttpServlet {

    private static final String SUCCESS = "checkout.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user != null && user.getRoleID() == 1) {

                String total = request.getParameter("orderTotal");
                CartDAO cartDao = new CartDAO();
                ProductDAO pDao = new ProductDAO();
                CartDTO userCart = cartDao.getCart(user.getId());
                List<ProductDTO> cartPList = userCart.getCart();
                if (cartPList.isEmpty()) {
                    url = ERROR;
                } else {
                    url = SUCCESS;
                    List<ProductDTO> pList = pDao.getAllProducts();
                    cartDao.removeCart(user.getId());
                    String orderID = cartDao.addOrder(user.getId(), total);
                    for (ProductDTO cartProduct : cartPList) {
                        cartProduct.setName(getProductName(cartProduct.getProductID(), pList));
                        int totalPrice = cartProduct.getQuantity() * getProductPrice(cartProduct.getProductID(), pList);
                        cartProduct.setPrice(totalPrice);
                        cartDao.addOrder(orderID, cartProduct.getProductID(), cartProduct.getQuantity(), totalPrice);
                    }
                    request.setAttribute("CART_PRODUCT_LIST", cartPList);
                    request.setAttribute("ORDER_TOTAL", total);
                }
            }
        } catch (Exception e) {
            log("Error at cart servlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private static String getProductName(String productID, List<ProductDTO> pList) {
        for (ProductDTO p : pList) {
            if (productID.equals(p.getProductID())) {
                return p.getName();
            }
        }
        return "";
    }

    private static int getProductPrice(String productID, List<ProductDTO> pList) {
        for (ProductDTO p : pList) {
            if (productID.equals(p.getProductID())) {
                return p.getPrice();
            }
        }
        return 0;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
