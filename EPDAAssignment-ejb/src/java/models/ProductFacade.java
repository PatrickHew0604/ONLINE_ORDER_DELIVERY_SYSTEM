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
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "EPDAAssignment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    public List<Product> findIsForSoldProduct() {
        TypedQuery<Product> query = em.createQuery(
                "SELECT a FROM Product a WHERE a.isForSold = true", Product.class
        );
        return query.getResultList();
    }

}
