/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Gender;
import models.MyUser;
import models.MyUserFacade;
import models.Role;

/**
 *
 * @author wengk
 */
public class EditCustomerOwnProfile extends HttpServlet {

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
        HttpSession currentSession = request.getSession();

        String username = request.getParameter("username");
        if (username != null) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password") == null
                    ? request.getParameter("hiddenPassword") : request.getParameter("password");
            Gender gender = Gender.valueOf(request.getParameter("gender"));
            String phoneNumber = request.getParameter("phoneNumber");
            String identityCardNumber = request.getParameter("identityCardNumber");
            String address = request.getParameter("address");

            MyUser newUser = new MyUser(username, password, Role.CUSTOMER, name, email, gender, phoneNumber, identityCardNumber, address,true);
            myUserFacade.edit(newUser);
            currentSession.setAttribute("loginUser", newUser);
        }
        MyUser loginUser = (MyUser) currentSession.getAttribute("loginUser");
        request.setAttribute("updatedUser", loginUser);

        request.setAttribute("action", "EditCustomerOwnProfile");
        request.setAttribute("href", "CustomerMainPage");

        request.getRequestDispatcher("editIndividualProfile.jsp").include(request, response);
        if (username != null) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<br><br><br> Your profile have been updated!");
            }
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
