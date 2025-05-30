/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.deliverystaff;

import java.io.IOException;
import java.util.Properties;
import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.MyOrder;
import models.MyOrderFacade;
import models.PurchaseProduct;

/**
 *
 * @author wengk
 */
public class PaymentReceivedServlet extends HttpServlet {

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
        String orderID = request.getParameter("orderID");
        MyOrder order = myOrderFacade.find(orderID);

        String to = order.getCustomer().getEmail();
        String from = "wengkanghew88@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("wengkanghew88@gmail.com", "lzgv wqmi lwlh lhvk");
            }
        });

        try {
            // Create a new email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Email subject
            message.setSubject("Order Receipt - Order ID: " + order.getOrderID());

            // Construct the email body (HTML formatted for better appearance)
            StringBuilder emailBody = new StringBuilder();
            emailBody.append("<html><body>");
            emailBody.append("<h1>Order Receipt</h1>");
            emailBody.append("<p>Thank you for your purchase! Below are your order details:</p>");

            emailBody.append("<p><strong>Order ID:</strong> ").append(order.getOrderID()).append("</p>");
            emailBody.append("<p><strong>Customer Name:</strong> ").append(order.getCustomer().getName()).append("</p>");
            emailBody.append("<p><strong>Order Date:</strong> ").append(order.getOrderDate()).append("</p>");
            emailBody.append("<p><strong>Status:</strong> ").append(order.getOrderStatus()).append("</p>");

            emailBody.append("<h2>Ordered Items</h2>");
            emailBody.append("<table style='width:100%; border-collapse: collapse; text-align: left;'>");
            emailBody.append("<thead><tr>");
            emailBody.append("<th style='border: 1px solid #ddd; padding: 8px;'>Product</th>");
            emailBody.append("<th style='border: 1px solid #ddd; padding: 8px;'>Quantity</th>");
            emailBody.append("<th style='border: 1px solid #ddd; padding: 8px;'>Price</th>");
            emailBody.append("<th style='border: 1px solid #ddd; padding: 8px;'>Total</th>");
            emailBody.append("</tr></thead><tbody>");

            // Loop through the ordered products and add them to the table
            double totalPrice = 0.0;
            for (PurchaseProduct product : order.getProducts()) {
                double itemTotal = product.getMatchProduct().getPrice() * product.getQuantity();
                totalPrice += itemTotal;

                emailBody.append("<tr>");
                emailBody.append("<td style='border: 1px solid #ddd; padding: 8px;'>")
                        .append(product.getMatchProduct().getProductName()).append("</td>");
                emailBody.append("<td style='border: 1px solid #ddd; padding: 8px;'>")
                        .append(product.getQuantity()).append("</td>");
                emailBody.append("<td style='border: 1px solid #ddd; padding: 8px;'>")
                        .append(String.format("RM %.2f", product.getMatchProduct().getPrice())).append("</td>");
                emailBody.append("<td style='border: 1px solid #ddd; padding: 8px;'>")
                        .append(String.format("RM %.2f", itemTotal)).append("</td>");
                emailBody.append("</tr>");
            }

            emailBody.append("</tbody></table>");
            emailBody.append("<p><strong>Total Price:</strong> ").append(String.format("RM %.2f", totalPrice)).append("</p>");
            emailBody.append("<p>If you have any questions, feel free to contact us.</p>");
            emailBody.append("<p>Best regards,</p>");
            emailBody.append("<p>Your Store Team</p>");
            emailBody.append("</body></html>");

            // Set the email content type and body
            message.setContent(emailBody.toString(), "text/html");

            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        request.getRequestDispatcher(
                "DeliveryStaffViewAssignedOrderPage").forward(request, response);
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
