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
import starlight.model.user.GoogleUserDTO;
import starlight.model.user.UserDAO;
import starlight.model.user.UserDTO;
import starlight.model.user.UserError;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    private static final String SUCCESS = "home.jsp";
    private static final String ERROR = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            GoogleUserDTO googleUser = (GoogleUserDTO) request.getAttribute("googleUser");
            UserDAO userDao = new UserDAO();
            if (googleUser != null) {
                UserDTO systemUser = userDao.geGoogleUser(googleUser.getEmail(), googleUser.getId());
                if (systemUser == null) {
                    systemUser = userDao.createUserFromGoogleLogin(googleUser);
                }
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", systemUser);
                url = SUCCESS;
            } else {
                String _user = request.getParameter("username");
                String pass = request.getParameter("password");
                UserDTO systemUser = userDao.loginSystemUser(_user, pass);
                if (systemUser == null) {
                    url = ERROR;
                    UserError error = new UserError();
                    error.addLoginError("Wrong login information!!!");
                    request.setAttribute("LOGIN_ERROR", error.toString());
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_USER", systemUser);
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at login servlet: " + e.toString());
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
