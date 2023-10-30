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
import starlight.model.shop.CategoryDAO;
import starlight.model.shop.CategoryDTO;
import starlight.model.shop.ProductDAO;
import starlight.model.shop.ProductDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Shop", urlPatterns = {"/shop"})
public class Shop extends HttpServlet {

    private static final String SUCCESS = "shop.jsp";
    private static final String ERROR = "shop.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String cateID = request.getParameter("cateID");
            String keyword = request.getParameter("search");
            ProductDAO pDao = new ProductDAO();
            CategoryDAO cDao = new CategoryDAO();
            List<CategoryDTO> cList = cDao.getAllCates();
            url = SUCCESS;
            if (keyword == null || keyword.isEmpty()) {
                List<ProductDTO> pList = cateID.equals("0") ? pDao.getAllProducts() : pDao.getProducts(cateID);
                request.setAttribute("PRODUCT_LIST", pList);
            }
            request.setAttribute("CATEGORY_LIST", cList);
            request.setAttribute("cate", cateID);
        } catch (Exception e) {
            log("Error at shop servlet: " + e.toString());
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
