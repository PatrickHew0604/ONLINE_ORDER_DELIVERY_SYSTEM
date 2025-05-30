/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author wengk
 */
@Stateless
public class MyOrderFacade extends AbstractFacade<MyOrder> {

    @PersistenceContext(unitName = "EPDAAssignment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MyOrderFacade() {
        super(MyOrder.class);
    }

    public List<MyOrder> findByProduct(String productID) {
        TypedQuery<MyOrder> query = em.createQuery(
                "SELECT o FROM MyOrder o JOIN o.products p WHERE p.matchProduct.productID = :productID", MyOrder.class
        );
        query.setParameter("productID", productID);
        return query.getResultList();
    }
    
        // Method to find orders by customer username
    public List<MyOrder> findByCustomer(String username) {
        TypedQuery<MyOrder> query = em.createQuery(
                "SELECT o FROM MyOrder o WHERE o.customer.username = :username", MyOrder.class
        );
        query.setParameter("username", username);
        return query.getResultList();
    }

}
