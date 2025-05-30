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
public class AddToCart extends HttpServlet {

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
        // Retrieve logged-in user
        MyUser loginUser = (MyUser) session.getAttribute("loginUser");
        String productID = request.getParameter("productID");

        if (productID != null) {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Product addedProduct = productFacade.find(productID);
            PurchaseProduct purchaseProduct = new PurchaseProduct(quantity, addedProduct);
            String displayMessage = null;

            ArrayList<PurchaseProduct> shoppingCart
                    = (ArrayList<PurchaseProduct>) session.getAttribute("shoppingCartProducts");
            if (shoppingCart == null) {
                shoppingCart = new ArrayList<PurchaseProduct>();
                shoppingCart.add(purchaseProduct);
            } else if (shoppingCart.contains(purchaseProduct)) {
                Product existingStockProduct = productFacade.find(productID);
                PurchaseProduct productAddedInCart = shoppingCart.get(shoppingCart.indexOf(purchaseProduct));
                if ((productAddedInCart.getQuantity() + quantity) > existingStockProduct.getQuantity()) {
                    displayMessage = "Sorry we dont have enough stock, consider buying lesser";
                } else {
                    productAddedInCart.setQuantity(productAddedInCart.getQuantity() + quantity);
                }
            } else {
                shoppingCart.add(purchaseProduct);
            }

//            Mini Cart Below
            session.setAttribute("shoppingCartProducts", shoppingCart);
            saveCartToCookies(shoppingCart, loginUser.getUsername(), response);
            request.setAttribute("displayMessage", displayMessage);
            request.getRequestDispatcher("CustomerMainPage").forward(request, response);
//            try (PrintWriter out = response.getWriter()) {
//                out.println(displayMessage);
//            }
        }
    }

    // Method to save cart items to cookies
    private void saveCartToCookies(ArrayList<PurchaseProduct> shoppingCart, String loginUsername, HttpServletResponse response) throws IOException {
        StringBuilder cartData = new StringBuilder();

        for (PurchaseProduct item : shoppingCart) {
            cartData.append(item.getMatchProduct().getProductID())
                    .append(":")
                    .append(item.getQuantity())
                    .append(",");
        }

        // Store cart data in a cookie specific to the user
        String encodedCartData = URLEncoder.encode(cartData.toString(), "UTF-8");
        Cookie cartCookie = new Cookie("cart_" + loginUsername, encodedCartData);
        cartCookie.setMaxAge(60 * 60 * 24 * 7); // Cookie valid for 7 days
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
