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
import models.MyOrder;
import models.MyOrderFacade;
import models.Product;
import models.ProductFacade;

/**
 *
 * @author wengk
 */
public class DeleteProduct extends HttpServlet {

    @EJB
    private MyOrderFacade myOrderFacade;

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
        String resultMessage = "Product has been deleted sucessfully !";
        String productID = request.getParameter("productID");
        if (productID != null) {
            Product productToDelete = productFacade.find(productID);
            if (productToDelete != null) {
                // Check if there are orders related to this product
                List<MyOrder> relatedOrders = myOrderFacade.findByProduct(productID);

                if (relatedOrders.isEmpty()) {
                    // Safe to delete the product
                    productFacade.remove(productToDelete);
                } else {
                    // Cannot delete, there are related orders
                    productToDelete.setIsForSold(false);
                    productFacade.edit(productToDelete);
                    resultMessage = "The product has been purchased by customer before, thus it is hide from the store !";
                }
            }
        }

        List<Product> products = productFacade.findAll();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (!product.isIsForSold()) {
                iterator.remove();  // Safe removal using the iterator
            }
        }
        request.setAttribute("products", products);
        request.getRequestDispatcher("deleteProduct.jsp").include(request, response);

//        if (productID != null) {
//            try (PrintWriter out = response.getWriter()) {
//                out.println(resultMessage);
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
