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
public class Receipt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String receiptID;
    @OneToOne
    private MyOrder order;
    private double paymentAmount;
    private Date paymentDate;

    public Receipt() {
    }

    public Receipt(MyOrder order, double paymentAmount, Date paymentDate) {
        this.order = order;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    public MyOrder getOrder() {
        return order;
    }

    public void setOrder(MyOrder order) {
        this.order = order;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String id) {
        this.receiptID = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (receiptID != null ? receiptID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receipt)) {
            return false;
        }
        Receipt other = (Receipt) object;
        if ((this.receiptID == null && other.receiptID != null) || (this.receiptID != null && !this.receiptID.equals(other.receiptID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Receipt[ id=" + receiptID + " ]";
    }

}
