/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.MyOrder;
import models.MyOrderFacade;
import models.MyUser;
import models.OrderStatus;
import models.Product;
import models.ProductFacade;
import models.PurchaseProduct;

/**
 *
 * @author wengk
 */
public class PlaceOrder extends HttpServlet {

    @EJB
    private ProductFacade productFacade;

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
        HttpSession currentSession = request.getSession();
        ArrayList<PurchaseProduct> shoppingCart
                = (ArrayList<PurchaseProduct>) currentSession.getAttribute("shoppingCartProducts");
        MyUser loginUser = (MyUser) currentSession.getAttribute("loginUser");

        // Create the order and persist it
        MyOrder order = new MyOrder(loginUser, OrderStatus.ORDERRECEIVED, new Date(), shoppingCart);
        myOrderFacade.create(order);

        // Update stock for each product in the cart
        for (PurchaseProduct purchaseItem : shoppingCart) {
            String existingShopProductID = purchaseItem.getMatchProduct().getProductID();
            Product existingShopProduct = productFacade.find(existingShopProductID);
            existingShopProduct.setQuantity(existingShopProduct.getQuantity() - purchaseItem.getQuantity());
            productFacade.edit(existingShopProduct);
        }

        // Clear the session shopping cart
        currentSession.setAttribute("shoppingCartProducts", new ArrayList<PurchaseProduct>());

        Cookie cartCookie = new Cookie("cart_" + loginUser, URLEncoder.encode("", "UTF-8"));
        cartCookie.setMaxAge(0); // Set cookie to expire in 7 days
        response.addCookie(cartCookie);

        // Respond with a thank-you page
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Thank You</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f9f9f9; margin: 0; padding: 0; }");
            out.println(".container { margin: 50px auto; width: 50%; background-color: white; padding: 40px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); text-align: center; }");
            out.println("h1 { color: #333; margin-bottom: 20px; }");
            out.println("p { font-size: 18px; color: #555; }");
            out.println(".center { text-align: center; margin-top: 30px; }");
            out.println(".btn { background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; margin: 5px; }");
            out.println(".btn:hover { background-color: #45a049; }");
            out.println("#countdown { font-weight: bold; }");
            out.println("</style>");
            out.println("<script>");
            out.println("var countdown = 5;");
            out.println("var countdownTimer = setInterval(function() {");
            out.println("document.getElementById('countdown').innerHTML = countdown;");
            out.println("countdown--;");
            out.println("if (countdown < 0) {");
            out.println("clearInterval(countdownTimer);");
            out.println("window.location.href = 'CustomerMainPage';");
            out.println("}");
            out.println("}, 1000);");
            out.println("</script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h1>Thank You for Your Purchase!</h1>");
            out.println("<p>Your order has been placed and is now being processed.</p>");
            out.println("<p>You will be redirected to the main page in <span id='countdown'>5</span> seconds.</p>");
            out.println("<p>If you're not redirected, <a href='CustomerMainPage' class='btn'>Click here to go back</a>.</p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
