/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Feedback;
import models.FeedbackFacade;
import models.MyOrder;
import models.MyOrderFacade;
import models.MyUser;
import models.OrderStatus;

/**
 *
 * @author wengk
 */
public class GiveRating extends HttpServlet {

    @EJB
    private MyOrderFacade myOrderFacade;

    @EJB
    private FeedbackFacade feedbackFacade;

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
        HttpSession session = request.getSession();
        MyUser loginUser = (MyUser) session.getAttribute("loginUser");

        // Get parameters from request
        String orderID = request.getParameter("orderID");
        String customerID = request.getParameter("customerID");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        // Fetch the order and validate if it's the correct customer
        MyOrder order = myOrderFacade.find(orderID);
        if (order != null && order.getCustomer().getUsername().equals(customerID)) {
            // Create a new feedback entity
            Feedback feedback = new Feedback();
            feedback.setOrder(order);
            feedback.setCustomer(loginUser);
            feedback.setRating(rating);
            feedback.setComment(comment);
            feedback.setFeedbackDate(new Date());

            // Persist feedback in the database
            feedbackFacade.create(feedback);

            // Optionally update order status (if needed)
            order.setOrderStatus(OrderStatus.RATED);
            myOrderFacade.edit(order);

            // Redirect back to customer orders page
            response.sendRedirect("CustomerViewOwnOrderPage");
        } else {
            // Handle invalid order or customer case
            request.setAttribute("errorMessage", "Invalid order or customer.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
