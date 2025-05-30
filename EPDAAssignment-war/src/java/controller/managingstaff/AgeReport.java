/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.managingstaff;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.MyUser;
import models.MyUserFacade;
import models.Role;

/**
 *
 * @author wengk
 */
public class AgeReport extends HttpServlet {

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

        Map<String, Integer> ageGroups = new HashMap<>();
        List<MyUser> users = myUserFacade.findAll();
        ageGroups.put("18-25", 0);
        ageGroups.put("26-35", 0);
        ageGroups.put("36-45", 0);
        ageGroups.put("46-60", 0);
        ageGroups.put("60+", 0);
        for (MyUser user : users) {
            if (user.getIdentityCardNumber() != null && user.getRole() == Role.CUSTOMER && user.isIsAvailable()) {
                int age = calculateAgeFromNRIC(user.getIdentityCardNumber());

                if (age >= 18 && age <= 25) {
                    ageGroups.put("18-25", ageGroups.get("18-25") + 1);
                } else if (age >= 26 && age <= 35) {
                    ageGroups.put("26-35", ageGroups.get("26-35") + 1);
                } else if (age >= 36 && age <= 45) {
                    ageGroups.put("36-45", ageGroups.get("36-45") + 1);
                } else if (age >= 46 && age <= 60) {
                    ageGroups.put("46-60", ageGroups.get("46-60") + 1);
                } else if (age > 60) {
                    ageGroups.put("60+", ageGroups.get("60+") + 1);
                }
            }
        }
        System.out.println(ageGroups);
        request.setAttribute("currentDate", new Date());
        request.setAttribute("ageGroups", ageGroups);
        request.getRequestDispatcher("ageReport.jsp").forward(request, response);
    }

    private int calculateAgeFromNRIC(String nric) {
        int year = Integer.parseInt(nric.substring(0, 2));
        int month = Integer.parseInt(nric.substring(2, 4));
        int day = Integer.parseInt(nric.substring(4, 6));

        // Adjust year for 1900s and 2000s
        int currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100;
        year += (year > currentYear ? 1900 : 2000);

        // Calculate age
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(year, month - 1, day);
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--; // Adjust if birth date has not occurred yet this year
        }
        return age;
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
