/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.deliverystaff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Assignment;
import models.AssignmentFacade;
import models.Feedback;
import models.FeedbackFacade;
import models.MyOrder;
import models.MyOrderFacade;
import models.MyUser;
import models.OrderStatus;
import models.PurchaseProduct;
import models.Receipt;
import models.ReceiptFacade;
import static models.Receipt_.paymentAmount;

/**
 *
 * @author wengk
 */
public class DeliveryStaffViewAssignedOrderPage extends HttpServlet {

    @EJB
    private ReceiptFacade receiptFacade;

    @EJB
    private FeedbackFacade feedbackFacade;

    @EJB
    private AssignmentFacade assignmentFacade;

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

        String action = request.getParameter("action");
        String orderID = request.getParameter("orderID");

        if (orderID != null) {
            MyOrder order = myOrderFacade.find(orderID);
            if (order != null && action!= null) {
                switch (action) {
                    case "updateDeliveryStatus":
                        order.setOrderStatus(OrderStatus.DELIVERED);
                        myOrderFacade.edit(order);
                        break;
                    case "generateReceipt":
                        // Calculate total price
                        double totalPrice = 0;
                        for (PurchaseProduct purchaseItem : order.getProducts()) {
                            totalPrice += purchaseItem.getMatchProduct().getPrice() * purchaseItem.getQuantity();
                        }

                        Receipt existingReceipt = receiptFacade.findByOrder(order);

                        if (existingReceipt == null) {
                            // No existing receipt, create a new one// Assuming order has a method to get total amount
                            Date paymentDate = new Date();  // Current date as payment date

                            Receipt newReceipt = new Receipt(order, totalPrice, paymentDate);
                            receiptFacade.create(newReceipt);
                        }
                        request.setAttribute("order", order);

                        request.setAttribute("totalPrice", totalPrice);

                        // Forward to the receipt.jsp page to display the receipt
                        request.getRequestDispatcher("receipt.jsp").forward(request, response);
                        return;
                    case "viewRating":
                        List<Feedback> feedbackList = feedbackFacade.findAll();
                        feedbackList.removeIf(feedback -> !feedback.getOrder().getOrderID().equals(orderID));
                        request.setAttribute("feedback", feedbackList.get(0)); // Assuming one feedback per order
                        request.setAttribute("order", feedbackList.get(0).getOrder());
                        request.setAttribute("goBackAction", "DeliveryStaffViewAssignedOrderPage");
                        request.getRequestDispatcher("viewRatings.jsp").forward(request, response);
                        return;
                }
            }
        }

        // Fetch updated order list
        List<Assignment> assignmentList = assignmentFacade.findAll();

        HttpSession currentSession = request.getSession();
        MyUser loginUser = (MyUser) currentSession.getAttribute("loginUser");

        ArrayList<MyOrder> orderList = new ArrayList<MyOrder>();
        for (Assignment assignment : assignmentList) {
            if (assignment.getDeliveryStaff().equals(loginUser)) {
                orderList.add(assignment.getOrder());
            }
        }

        request.setAttribute(
                "orders", orderList);
        request.setAttribute(
                "NoOrderMessage", "No Orders Has Been Assigned To You");

        // Forward to JSP
        request.getRequestDispatcher(
                "deliveryStaffViewAssignedOrderPage.jsp").forward(request, response);
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
