/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.MyOrder;
import models.MyOrderFacade;
import models.MyUser;
import models.PurchaseProduct;

/**
 *
 * @author wengk
 */
public class EditCustomerOwnOrder extends HttpServlet {

    @EJB
    private MyOrderFacade myOrderFacade;



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
        // Get the current session
        HttpSession session = request.getSession();
        MyUser loginUser = (MyUser) session.getAttribute("loginUser");

        // Retrieve the order ID from the request (assuming it's passed in the session or request)
        String orderID = request.getParameter("orderID");
        MyOrder order = myOrderFacade.find(orderID); // Fetch the order by ID

        if (order != null && order.getCustomer().equals(loginUser)) {
            String action = request.getParameter("action");

            // Loop through the products in the order
            for (PurchaseProduct product : order.getProducts()) {
                String productId = product.getMatchProduct().getProductID();
                String quantityParam = request.getParameter("quantity-" + productId);

                // Handle update action
                if (action != null && action.startsWith("update-") && quantityParam != null) {
                    int newQuantity = Integer.parseInt(quantityParam);
                    product.setQuantity(newQuantity); // Update quantity
                }

                // Handle remove action
                if (action != null && action.startsWith("remove-")) {
                    order.getProducts().remove(product); // Remove the product from the order
                    break; // Exit loop after removing to avoid ConcurrentModificationException
                }
            }
            myOrderFacade.remove(order);
            myOrderFacade.create(order);

            // Redirect or forward to the edit order page
            request.setAttribute("order", order);
            request.setAttribute("totalPrice", calculateTotalPrice(order)); // Helper method to calculate total
            request.getRequestDispatcher("editCustomerOwnOrderPage.jsp").forward(request, response);
        }
    }

    private double calculateTotalPrice(MyOrder order) {
        double totalPrice = 0;
        for (PurchaseProduct product : order.getProducts()) {
            totalPrice += product.getMatchProduct().getPrice() * product.getQuantity();
        }
        return totalPrice;
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
