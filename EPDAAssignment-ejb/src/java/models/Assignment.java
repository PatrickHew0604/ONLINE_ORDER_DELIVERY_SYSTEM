/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author wengk
 */
@Entity
public class Assignment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String assignmentID;
    @OneToOne
    private MyOrder order;
    @OneToOne
    private MyUser deliveryStaff;
    private Date assignmentDate;

    public Assignment() {
    }

    public Assignment(MyOrder order, MyUser deliveryStaff, Date assignmentDate) {
        this.order = order;
        this.deliveryStaff = deliveryStaff;
        this.assignmentDate = assignmentDate;
    }

    public MyOrder getOrder() {
        return order;
    }

    public void setOrder(MyOrder order) {
        this.order = order;
    }

    public MyUser getDeliveryStaff() {
        return deliveryStaff;
    }

    public void setDeliveryStaff(MyUser deliveryStaff) {
        this.deliveryStaff = deliveryStaff;
    }

    public Date getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public String getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(String id) {
        this.assignmentID = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assignmentID != null ? assignmentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assignment)) {
            return false;
        }
        Assignment other = (Assignment) object;
        if ((this.assignmentID == null && other.assignmentID != null) || (this.assignmentID != null && !this.assignmentID.equals(other.assignmentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Assignment[ id=" + assignmentID + " ]";
    }

}
