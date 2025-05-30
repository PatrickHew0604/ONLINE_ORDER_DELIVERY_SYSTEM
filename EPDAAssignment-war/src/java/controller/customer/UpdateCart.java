/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.MyUser;
import models.Product;
import models.ProductFacade;
import models.PurchaseProduct;

/**
 *
 * @author wengk
 */
public class UpdateCart extends HttpServlet {

    @EJB
    private ProductFacade productFacade;

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
        HttpSession session = request.getSession();
        MyUser loginUser = (MyUser) session.getAttribute("loginUser");
        ArrayList<PurchaseProduct> shoppingCart = (ArrayList<PurchaseProduct>) session.getAttribute("shoppingCartProducts");
        String printedMessage = null;

        String action = request.getParameter("action");
        if (action != null) {
            for (PurchaseProduct product : shoppingCart) {
                String productID = product.getMatchProduct().getProductID();
                if (action.equals("update-" + productID)) {
                    // Update quantity for the given product
                    String newQuantityStr = request.getParameter("quantity-" + productID);
                    if (newQuantityStr != null) {
                        int newQuantity = Integer.parseInt(newQuantityStr);
                        Product existingStockProduct = productFacade.find(productID);
                        if (newQuantity > existingStockProduct.getQuantity()) {
                            printedMessage = "Sorry we dont have enough stock, consider buying lesser";
                            break;
                        } else {
                            product.setQuantity(newQuantity);
//                            printedMessage = "Product has been updated !";
                        }
                    }
                } else if (action.equals("remove-" + productID)) {
                    // Remove the product from the cart
                    shoppingCart.remove(product);
//                    printedMessage = "Product has been removed !";
                    break; // Exit the loop to avoid concurrent modification issues
                }
                session.setAttribute("shoppingCartProducts", shoppingCart);
            }
            updateCartCookie(response, shoppingCart, loginUser.getUsername());
        }

        double totalPrice = 0;
        for (PurchaseProduct product : shoppingCart) {
            totalPrice += product.getMatchProduct().getPrice() * product.getQuantity();
        }
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("displayMessage", printedMessage);
        request.getRequestDispatcher("updateCart.jsp").forward(request, response);

//        if (action != null) {
//            try (PrintWriter out = response.getWriter()) {
//                out.println(printedMessage);
//            }
//        }
    }

    // Helper method to update the cart cookie
    private void updateCartCookie(HttpServletResponse response, ArrayList<PurchaseProduct> shoppingCart, String loginUser) throws IOException {
        StringBuilder encodedCartData = new StringBuilder();
        for (PurchaseProduct product : shoppingCart) {
            String productID = product.getMatchProduct().getProductID();
            int quantity = product.getQuantity();
            encodedCartData.append(productID).append(":").append(quantity).append(",");
        }

        // Remove the trailing comma
        if (encodedCartData.length() > 0) {
            encodedCartData.setLength(encodedCartData.length() - 1);
        }

        // Create or update the cart cookie with the encoded cart data
        Cookie cartCookie = new Cookie("cart_" + loginUser, URLEncoder.encode(encodedCartData.toString(), "UTF-8"));
        cartCookie.setMaxAge(60 * 60 * 24 * 7); // Set cookie to expire in 7 days
        response.addCookie(cartCookie);
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
