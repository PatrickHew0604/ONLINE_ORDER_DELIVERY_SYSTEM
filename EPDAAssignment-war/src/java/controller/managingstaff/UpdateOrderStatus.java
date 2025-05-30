/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.managingstaff;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.MyOrder;
import models.MyOrderFacade;
import models.OrderStatus;

/**
 *
 * @author wengk
 */
@WebServlet(name = "UpdateOrderStatus", urlPatterns = {"/UpdateOrderStatus"})
public class UpdateOrderStatus extends HttpServlet {

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

            if (order != null) {
                switch (action) {
                    case "assignDeliveryStaff":
                        HttpSession currentSession = request.getSession();
                        currentSession.setAttribute("assigningOrder", order);
                        request.getRequestDispatcher("AssignOrderToDeliveryStaff").forward(request, response);
                        return;
                    case "updateStatus":
                        // Update status based on the current status
                        switch (order.getOrderStatus()) {
                            case ORDERRECEIVED:
                                order.setOrderStatus(OrderStatus.PREPARING);
                                break;
                            case PREPARING:
                                order.setOrderStatus(OrderStatus.READYFORDELIVERY);
                                break;
                        }
                        myOrderFacade.edit(order); // Save changes
                        break;
                    case "revertStatus":
                        // Revert the status to the previous stage
                        switch (order.getOrderStatus()) {
                            case READYFORDELIVERY:
                                order.setOrderStatus(OrderStatus.PREPARING);
                                break;
                            case PREPARING:
                                order.setOrderStatus(OrderStatus.ORDERRECEIVED);
                                break;
                            }
                        myOrderFacade.edit(order); // Save changes
                        break;
                    default:
                        break;
                }
            }
        }

        // Fetch updated order list
        List<MyOrder> orderList = myOrderFacade.findAll();

        request.setAttribute("orders", orderList);
        request.setAttribute("buttonName", "Update Delivery Status");
        request.setAttribute("NoOrderMessage", "No Orders Available");

        // Forward to JSP
        request.getRequestDispatcher("managingStaffUpdateOrderStatus.jsp").forward(request, response);
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
