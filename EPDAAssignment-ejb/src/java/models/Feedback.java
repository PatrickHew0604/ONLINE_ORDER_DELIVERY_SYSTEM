/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
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
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String feedbackID;
    @OneToOne
    private MyOrder order;
    @OneToOne
    private MyUser customer;
    private int rating;
    private String comment;
    private Date feedbackDate;

    public Feedback() {
    }

    public Feedback(String feedbackID, MyOrder order, MyUser customer, int rating, String comment, Date feedbackDate) {
        this.feedbackID = feedbackID;
        this.order = order;
        this.customer = customer;
        this.rating = rating;
        this.comment = comment;
        this.feedbackDate = feedbackDate;
    }

    public MyOrder getOrder() {
        return order;
    }

    public void setOrder(MyOrder order) {
        this.order = order;
    }

    public MyUser getCustomer() {
        return customer;
    }

    public void setCustomer(MyUser customer) {
        this.customer = customer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String id) {
        this.feedbackID = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackID != null ? feedbackID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.feedbackID == null && other.feedbackID != null) || (this.feedbackID != null && !this.feedbackID.equals(other.feedbackID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Feedback[ id=" + feedbackID + " ]";
    }

}
