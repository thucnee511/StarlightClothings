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
@WebServlet(name = "Cart", urlPatterns = {"/cart"})
public class Cart extends HttpServlet {

    private static final String SUCCESS = "cart.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user != null && user.getRoleID() == 1) {
                url = SUCCESS;
                CartDAO cartDao = new CartDAO();
                ProductDAO pDao = new ProductDAO();
                CartDTO userCart = cartDao.getCart(user.getId());
                List<ProductDTO> pList = pDao.getAllProducts();
                List<ProductDTO> cartPList = userCart.getCart();
                int totalPrice = 0;
                for (ProductDTO cartProduct : cartPList) {
                    ProductDTO p = findProduct(cartProduct.getProductID(), pList);
                    cartProduct.setCateID(p.getCateID());
                    cartProduct.setImage(p.getImage());
                    cartProduct.setName(p.getName());
                    cartProduct.setPrice(p.getPrice());
                    cartProduct.setCateID(p.getCateID());
                    totalPrice += cartProduct.getPrice() * cartProduct.getQuantity();
                }
                request.setAttribute("CART_PRODUCT_LIST", cartPList);
                request.setAttribute("CART_TOTAL", totalPrice);
            }
        } catch (Exception e) {
            log("Error at cart servlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private static ProductDTO findProduct(String productID, List<ProductDTO> pList) {
        for (ProductDTO p : pList) {
            if (productID.equals(p.getProductID())) {
                return p;
            }
        }
        return null;
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
