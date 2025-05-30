/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wengk
 */
@Stateless
public class ReceiptFacade extends AbstractFacade<Receipt> {

    @PersistenceContext(unitName = "EPDAAssignment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReceiptFacade() {
        super(Receipt.class);
    }
    
    public Receipt findByOrder(MyOrder order) {
    try {
        return em.createQuery("SELECT r FROM Receipt r WHERE r.order = :order", Receipt.class)
                 .setParameter("order", order)
                 .getSingleResult();
    } catch (NoResultException e) {
        return null;  // No receipt found for the order
    }
}
    
}
