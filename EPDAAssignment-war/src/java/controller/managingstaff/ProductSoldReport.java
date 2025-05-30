/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.managingstaff;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.MyOrder;
import models.Product;
import models.PurchaseProduct;
import models.Receipt;
import models.ReceiptFacade;

/**
 *
 * @author wengk
 */
public class ProductSoldReport extends HttpServlet {

    @EJB
    private ReceiptFacade receiptFacade;

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
        // Maps to store product quantities sold and revenue generated
        Map<String, Integer> productSales = new HashMap<>();
        Map<String, Double> productRevenue = new HashMap<>();

        // Retrieve all receipts
        List<Receipt> receipts = receiptFacade.findAll();
        for (Receipt receipt : receipts) {
            MyOrder order = receipt.getOrder();
            for (PurchaseProduct purchaseProduct : order.getProducts()) {
                Product product = purchaseProduct.getMatchProduct();
                String productName = product.getProductName();
                int quantitySold = purchaseProduct.getQuantity();
                double productPrice = product.getPrice();

                // Update total quantity sold and revenue for each product
                productSales.put(productName, productSales.getOrDefault(productName, 0) + quantitySold);
                productRevenue.put(productName, productRevenue.getOrDefault(productName, 0.0) + (quantitySold * productPrice));
            }
        }

        // Set data as request attributes
        request.setAttribute("productSales", productSales);
        request.setAttribute("productRevenue", productRevenue);
        request.setAttribute("currentDate", new Date());
        request.getRequestDispatcher("productSoldReport.jsp").forward(request, response);
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
