/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starlight.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class MainControl extends HttpServlet {

    private static final String WELCOME_PAGE = "home.jsp";

    private static final String LOGIN_ACTION = "Login";
    private static final String LOGIN_CONTROL = "login";

    private static final String LOGOUT_ACTION = "Logout";
    private static final String LOGOUT_CONTROL = "logout";

    private static final String SIGNUP_ACTION = "Signup";
    private static final String SIGNUP_CONTROL = "signup";

    private static final String SHOP_ACTION = "Shop";
    private static final String SHOP_CONTROL = "shop";

    private static final String SHOPSEARCH_ACTION = "ShopSearch";
    private static final String SHOPSEARCH_CONTROL = "shopSearch";

    private static final String USERSEARCH_ACTION = "UserSearch";
    private static final String USERSEARCH_CONTROL = "userSearch";

    private static final String ADDCART_ACTION = "Add to Cart";
    private static final String ADDCART_CONTROL = "addToCart";

    private static final String VIEWCART_ACTION = "Cart";
    private static final String VIEWCART_CONTROL = "cart";

    private static final String UPDATECART_ACTION = "UpdateCart";
    private static final String UPDATECART_CONTROL = "updateCart";

    private static final String DELETECART_ACTION = "DeleteCart";
    private static final String DELETECART_CONTROL = "deleteCart";

    private static final String CHECKOUT_ACTION = "Checkout";
    private static final String CHECKOUT_CONTROL = "checkout";

    private static final String USER_ACTION = "User";
    private static final String USER_CONTROL = "user";

    private static final String UPDATEUSER_ACTION = "UpdateUser";
    private static final String UPDATEUSER_CONTROL = "updateUser";

    private static final String DELETEUSER_ACTION = "DeleteUser";
    private static final String DELETEUSER_CONTROL = "deleteUser";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = WELCOME_PAGE;
        try {
            String action = request.getParameter("action");
            if (action != null) {
                if (LOGIN_ACTION.equals(action)) {
                    url = LOGIN_CONTROL;
                } else if (LOGOUT_ACTION.equals(action)) {
                    url = LOGOUT_CONTROL;
                } else if (SIGNUP_ACTION.equals(action)) {
                    url = SIGNUP_CONTROL;
                } else if (SHOP_ACTION.equals(action)) {
                    url = SHOP_CONTROL;
                } else if (ADDCART_ACTION.equals(action)) {
                    url = ADDCART_CONTROL;
                } else if (SHOPSEARCH_ACTION.equals(action)) {
                    url = SHOPSEARCH_CONTROL;
                } else if (VIEWCART_ACTION.equals(action)) {
                    url = VIEWCART_CONTROL;
                } else if (UPDATECART_ACTION.equals(action)) {
                    url = UPDATECART_CONTROL;
                } else if (DELETECART_ACTION.equals(action)) {
                    url = DELETECART_CONTROL;
                } else if (CHECKOUT_ACTION.equals(action)) {
                    url = CHECKOUT_CONTROL;
                } else if (USER_ACTION.equals(action)) {
                    url = USER_CONTROL;
                } else if (UPDATEUSER_ACTION.equals(action)) {
                    url = UPDATEUSER_CONTROL;
                } else if (DELETEUSER_ACTION.equals(action)) {
                    url = DELETEUSER_CONTROL;
                } else if (USERSEARCH_ACTION.equals(action)) {
                    url = USERSEARCH_CONTROL;
                }
            }
        } catch (Exception e) {
            log("Exception at main controller: " + e.toString());
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
