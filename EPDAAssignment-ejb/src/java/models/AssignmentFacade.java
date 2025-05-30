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
public class AssignmentFacade extends AbstractFacade<Assignment> {

    @PersistenceContext(unitName = "EPDAAssignment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssignmentFacade() {
        super(Assignment.class);
    }

    public List<Assignment> findByDeliveryStaff(String username) {
        TypedQuery<Assignment> query = em.createQuery(
                "SELECT a FROM Assignment a WHERE a.deliveryStaff.username = :username", Assignment.class
        );
        query.setParameter("username", username);
        return query.getResultList();
    }

}
