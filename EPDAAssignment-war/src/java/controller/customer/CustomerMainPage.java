/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
public class CustomerMainPage extends HttpServlet {

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
        ArrayList<PurchaseProduct> shoppingCartFromCookies = null;
        // Retrieve logged-in user
        MyUser loginUser = (MyUser) session.getAttribute("loginUser");
        ArrayList<PurchaseProduct> shoppingCart
                = (ArrayList<PurchaseProduct>) session.getAttribute("shoppingCartProducts");

        if (shoppingCart == null) {
            shoppingCartFromCookies = loadCartFromCookies(request, loginUser.getUsername());
            if (!shoppingCartFromCookies.isEmpty()) {
                session.setAttribute("shoppingCartProducts", shoppingCartFromCookies);
            }
        }

        List<Product> productList = productFacade.findAll();
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (!product.isIsForSold()) {
                iterator.remove();  // Safe removal using the iterator
            }
        }

        request.setAttribute("cartProducts", shoppingCart == null ? (shoppingCartFromCookies == null ? null : shoppingCartFromCookies) : shoppingCart);

        request.setAttribute("products", productList);
        request.getRequestDispatcher("customerMainPage.jsp").forward(request, response);
    }

// Method to load cart items from cookies for the logged-in user
    private ArrayList<PurchaseProduct> loadCartFromCookies(HttpServletRequest request, String loginUsername) throws IOException {
        ArrayList<PurchaseProduct> shoppingCart = new ArrayList<>();

        // Find the cart cookie specific to the user
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart_" + loginUsername)) {
                    String cartData = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    String[] items = cartData.split(",");

                    for (String itemData : items) {
                        String[] productDetails = itemData.split(":");
                        if (productDetails.length < 2) {
                            // Skip any improperly formatted data
                            continue;
                        }

                        String productID = productDetails[0];
                        int quantity = Integer.parseInt(productDetails[1]);

                        // Retrieve product from the database
                        Product product = productFacade.find(productID);
                        if (product != null) {
                            if (product.getQuantity() > quantity) {
                                shoppingCart.add(new PurchaseProduct(quantity, product));
                            } else {
                                shoppingCart.add(new PurchaseProduct(product.getQuantity(), product));
                            }
                        }
                    }
                    break;
                }
            }
        }
        return shoppingCart;
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
