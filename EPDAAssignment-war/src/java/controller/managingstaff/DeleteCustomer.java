/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.managingstaff;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.MyOrder;
import models.MyOrderFacade;
import models.MyUser;
import models.MyUserFacade;
import models.OrderStatus;
import models.Role;

/**
 *
 * @author wengk
 */
public class DeleteCustomer extends HttpServlet {

    @EJB
    private MyOrderFacade myOrderFacade;

    @EJB
    private MyUserFacade myUserFacade;

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

        String deletedUsername = request.getParameter("deletedUsername");
        String displayMessage = deletedUsername + " has been sucessfully deleted";
        if (deletedUsername != null) {
            MyUser foundUser = myUserFacade.find(deletedUsername);

            // Fetch all orders for the customer
            List<MyOrder> customerOrders = myOrderFacade.findByCustomer(foundUser.getUsername());

            // Check if the customer has any ongoing orders
            boolean hasActiveOrders = customerOrders.stream()
                    .anyMatch(order -> order.getOrderStatus() == OrderStatus.PREPARING || order.getOrderStatus() == OrderStatus.ORDERRECEIVED
                    || order.getOrderStatus() == OrderStatus.READYFORDELIVERY
                    || order.getOrderStatus() == OrderStatus.DELIVERING);

            if (hasActiveOrders) {
                // Show a message that the customer cannot be deleted
                displayMessage = "Cannot delete user: This customer has ongoing orders.";
            } else {
                try {
                    // If there are no active orders, proceed with the deletion
                    myUserFacade.remove(foundUser);
                } catch (Exception e) {
                    foundUser.setIsAvailable(false);
                    myUserFacade.edit(foundUser);
                }
            }
        }

        List<MyUser> users = myUserFacade.findAll();
        users.removeIf(user -> user.getRole() == Role.MANAGINGSTAFF || user.getRole() == Role.DELIVERYSTAFF);
        users.removeIf(user -> user.isIsAvailable() == false);
        request.setAttribute("users", users);

        if (deletedUsername != null) {
            request.setAttribute("displayMessage", displayMessage);
        }
        request.getRequestDispatcher("deletecustomer.jsp").forward(request, response);
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
