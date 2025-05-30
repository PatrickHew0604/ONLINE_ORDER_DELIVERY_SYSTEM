/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.managingstaff;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
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
public class AddProduct extends HttpServlet {

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
        String productName = request.getParameter("productName");
        if (productName != null) {
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.valueOf(request.getParameter("quantity"));
            Product newProduct = new Product(productName, price, quantity, true);
            productFacade.create(newProduct);
        }

        List<Product> products = productFacade.findIsForSoldProduct();
//        Iterator<Product> iterator = products.iterator();
//        while (iterator.hasNext()) {
//            Product product = iterator.next();
//            if (!product.isIsForSold()) {
//                iterator.remove();  // Safe removal using the iterator
//            }
//        }
        request.setAttribute("products", products);
        request.getRequestDispatcher("addProduct.jsp").forward(request, response);

//        if (productName != null) {
//            try (PrintWriter out = response.getWriter()) {
//                if (!found) {
//                    out.println("<p>Product has been added sucessfully !</p>");
//                } else {
//                    out.println("<p>This Product ID has been Used !</p>");
//                }
//            }
//        }

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
