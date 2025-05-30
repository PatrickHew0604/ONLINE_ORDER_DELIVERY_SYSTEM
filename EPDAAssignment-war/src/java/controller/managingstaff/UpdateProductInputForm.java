/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.managingstaff;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Product;
import models.ProductFacade;

/**
 *
 * @author wengk
 */
public class UpdateProductInputForm extends HttpServlet {

    @EJB
    private ProductFacade productFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String productID = request.getParameter("productID");

        if (productID != null) {
            Product foundProduct = productFacade.find(productID);
            request.setAttribute("updatedProduct", foundProduct);
            request.getRequestDispatcher("updateProductInputForm.jsp").forward(request, response);
        }

        String updatedProductID = request.getParameter("updatedProductID");
        if (updatedProductID != null) {
            String productName = request.getParameter("productName");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.valueOf(request.getParameter("quantity"));
            Product updatedProduct = new Product(updatedProductID, productName, price, quantity,true);
            productFacade.edit(updatedProduct);

            request.getRequestDispatcher("UpdateProduct").include(request, response);

            try (PrintWriter out = response.getWriter()) {
                out.println("Product has been updated !");
            }
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
