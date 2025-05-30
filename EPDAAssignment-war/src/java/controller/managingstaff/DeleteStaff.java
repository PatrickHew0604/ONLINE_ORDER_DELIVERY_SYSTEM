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
import javax.servlet.http.HttpSession;
import models.Assignment;
import models.AssignmentFacade;
import models.MyUser;
import models.MyUserFacade;
import models.OrderStatus;
import models.Role;

/**
 *
 * @author wengk
 */
public class DeleteStaff extends HttpServlet {

    @EJB
    private AssignmentFacade assignmentFacade;

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

            // Fetch assignments for the delivery staff
            List<Assignment> assignments = assignmentFacade.findByDeliveryStaff(foundUser.getUsername());

            // Check if the user is handling any ongoing orders
            boolean hasActiveOrders = assignments.stream()
                    .anyMatch(assignment -> assignment.getOrder().getOrderStatus() == OrderStatus.DELIVERING);

            if (hasActiveOrders) {
                // Show a message that the user cannot be deleted
                // Forward to an error page or show the message in the current context
                displayMessage = "This delivery order has active order cannot be deleted !";
            } else if (!assignments.isEmpty()) {
                foundUser.setIsAvailable(false);
                myUserFacade.edit(foundUser);
            } else {
                // If there are no active orders, proceed with the deletion
                myUserFacade.remove(foundUser);
            }
        }

//      Avoid deleting the login user itself  
        HttpSession s = request.getSession(false);
        MyUser myUser = (MyUser) s.getAttribute("loginUser");

        List<MyUser> users = myUserFacade.findAll();
        users.remove(myUser);
        users.removeIf(user -> user.getRole() == Role.CUSTOMER);
        users.removeIf(user -> user.isIsAvailable() == false);

        request.setAttribute("users", users);

        if (deletedUsername != null) {
            request.setAttribute("displayMessage", displayMessage);
        }
        request.getRequestDispatcher("deletestaff.jsp").forward(request, response);
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
