/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.managingstaff;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Gender;
import models.MyUser;
import models.MyUserFacade;
import models.Role;

/**
 *
 * @author wengk
 */
public class UpdateStaffInputForm extends HttpServlet {

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
//        POP Out the EDIT FORM
        String updatedUsername = request.getParameter("updatedUsername");
        if (updatedUsername != null) {
            MyUser foundUser = myUserFacade.find(updatedUsername);
            request.setAttribute("updatedUser", foundUser);
            request.getRequestDispatcher("updatestaffinputform.jsp").forward(request, response);
        }

//        Update User
        String userName = request.getParameter("username");
        if (userName != null) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Role role = Role.valueOf(request.getParameter("role"));
            Gender gender = Gender.valueOf(request.getParameter("gender"));
            String phoneNumber = request.getParameter("phoneNumber");
            String identityCardNumber = request.getParameter("identityCardNumber");
            String address = request.getParameter("address");
            try (PrintWriter out = response.getWriter()) {

                MyUser newUser = null;
                switch (role) {
                    case MANAGINGSTAFF:
                        newUser = new MyUser(userName, password, role, name, email, gender, phoneNumber, identityCardNumber, address,true);
                        break;
                    case DELIVERYSTAFF:
                        newUser = new MyUser(userName, password, role, name, email, gender, phoneNumber, identityCardNumber, address,true);
                        break;
                }
                myUserFacade.edit(newUser);
                request.getRequestDispatcher("UpdateStaff").include(request, response);
                out.println("<br><br><br> The staff details have been updated!");
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
