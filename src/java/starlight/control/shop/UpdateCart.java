/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starlight.control.shop;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import starlight.model.shop.CartDAO;
import starlight.model.shop.ProductDAO;
import starlight.model.shop.ProductDTO;
import starlight.model.user.UserDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateCart", urlPatterns = {"/updateCart"})
public class UpdateCart extends HttpServlet {

    private static final String SUCCESS = "cart";
    private static final String ERROR = "cart";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            String productID = request.getParameter("productID");
            String quantity = request.getParameter("quantity");
            CartDAO cDao = new CartDAO();
            ProductDAO pDao = new ProductDAO();
            ProductDTO p = pDao.getroduct(productID);
            if (p.getQuantity() < Integer.parseInt(quantity)) {
                url = ERROR;
                request.setAttribute("errorMsg", "Remaining product(s) do not enough to update!!!");
            } else {
                cDao.updateCart(user.getId(), productID, quantity);
                request.setAttribute("successMsg", "Cart update successfully!!!");
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at update cart servlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
