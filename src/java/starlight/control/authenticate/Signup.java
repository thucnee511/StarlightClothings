/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package starlight.control.authenticate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import starlight.model.user.UserDAO;
import starlight.model.user.UserDTO;
import starlight.model.user.UserError;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Signup", urlPatterns = {"/signup"})
public class Signup extends HttpServlet {

    private static final String SUCCESS = "home.jsp";
    private static final String ERROR = "signup.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            UserDAO userDao = new UserDAO();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            UserDTO user = userDao.getUserByUsernameOrEmail(username, email);
            UserError error = new UserError();//UserError.validSignupInfo(password, confirmPassword, email);
            if (user != null) {
                error.addSignupError("Username or Email already exit!!!");
                request.setAttribute("SIGNUP_ERROR", error.toString());
                url = ERROR;
            } else {
                UserError _error = UserError.validSignupInfo(password, confirmPassword, email);
                if (_error != null) {
                    request.setAttribute("SIGNUP_ERROR", _error.toString());
                    url = ERROR;
                } else {
                    UserDTO _user = userDao.createSystemUser(username, password, email, phone, address);
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_USER", _user);
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at sign up servlet: " + e.toString());
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
