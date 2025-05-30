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
import models.Feedback;
import models.FeedbackFacade;
import models.MyOrder;
import models.MyOrderFacade;

/**
 *
 * @author wengk
 */
public class ViewOrders extends HttpServlet {

    @EJB
    private FeedbackFacade feedbackFacade;

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
        List<MyOrder> orderList = myOrderFacade.findAll();

        String orderID = request.getParameter("orderID");
        if (orderID != null) {
            List<Feedback> feedbackList = feedbackFacade.findAll();
            feedbackList.removeIf(feedback -> !feedback.getOrder().getOrderID().equals(orderID));
            request.setAttribute("feedback", feedbackList.get(0)); // Assuming one feedback per order
            request.setAttribute("order", feedbackList.get(0).getOrder());
            request.setAttribute("goBackAction", "ViewOrders");
            request.getRequestDispatcher("viewRatings.jsp").forward(request, response);
            return;
        }

        request.setAttribute("orders", orderList);
        request.setAttribute("buttonName", "View Details");
        request.setAttribute("NoOrderMessage", "No Orders Available");
        // TODO
        request.setAttribute("action", "View ViewOrderDetails");

        request.getRequestDispatcher("viewOrders.jsp").forward(request, response);
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
