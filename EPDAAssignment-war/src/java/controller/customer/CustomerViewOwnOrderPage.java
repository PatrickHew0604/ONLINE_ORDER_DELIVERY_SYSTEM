/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AssignmentFacade;
import models.Feedback;
import models.FeedbackFacade;
import models.MyOrder;
import models.MyOrderFacade;
import models.MyUser;
import models.Product;
import models.ProductFacade;
import models.PurchaseProduct;

/**
 *
 * @author wengk
 */
public class CustomerViewOwnOrderPage extends HttpServlet {

    @EJB
    private ProductFacade productFacade;

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
    @EJB
    private AssignmentFacade assignmentFacade;

    @EJB
    private MyOrderFacade myOrderFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession currentSession = request.getSession();
        MyUser loginUser = (MyUser) currentSession.getAttribute("loginUser");

        String action = request.getParameter("action");
        String orderID = request.getParameter("orderID");

        if (null != action) switch (action) {
            case "removeOrder":
                // Handle order removal
                MyOrder orderToRemove = myOrderFacade.find(orderID);
                if (orderToRemove != null && orderToRemove.getCustomer().equals(loginUser)) {
                    myOrderFacade.remove(orderToRemove);
                }   ArrayList<PurchaseProduct> purchasedProducts = orderToRemove.getProducts();
                for(PurchaseProduct purchasedProduct:purchasedProducts){
                    Product updateProduct = purchasedProduct.getMatchProduct();
                    updateProduct.setQuantity(updateProduct.getQuantity()+purchasedProduct.getQuantity());
                    productFacade.edit(updateProduct);
                }   break;
            case "giveRating":
                MyOrder orderToRate = myOrderFacade.find(orderID);
                request.setAttribute("loginUser", loginUser);
                request.setAttribute("order", orderToRate);
                request.getRequestDispatcher("giveRating.jsp").forward(request, response);
                return;
            case "viewRating":
                MyOrder order = myOrderFacade.find(orderID);
                List<Feedback> feedbackList = feedbackFacade.findAll();
                feedbackList.removeIf(feedback -> !feedback.getOrder().getOrderID().equals(orderID));
                request.setAttribute("feedback", feedbackList.get(0)); // Assuming one feedback per order
                request.setAttribute("order", feedbackList.get(0).getOrder());
                request.setAttribute("goBackAction", "CustomerViewOwnOrderPage");
                request.getRequestDispatcher("viewRatings.jsp").forward(request, response);
                return;
            default:
                break;
        }

        // Fetch updated orders after modifications
        List<MyOrder> orderList = myOrderFacade.findAll();
        ArrayList<MyOrder> customerOrders = new ArrayList<>();
        for (MyOrder order : orderList) {
            if (order.getCustomer().equals(loginUser)) {
                customerOrders.add(order);
            }
        }

        request.setAttribute("orders", customerOrders);
        if (customerOrders.isEmpty()) {
            request.setAttribute("NoOrderMessage", "No Orders Found for Your Account.");
        }

        // Forward to the JSP page to display the updated orders
        request.getRequestDispatcher("customerViewOwnOrderPage.jsp").forward(request, response);

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
