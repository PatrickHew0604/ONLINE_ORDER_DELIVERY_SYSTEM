/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
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
public class PurchaseProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String purchaseProductID;
    private int quantity;
    @OneToOne
    private Product matchProduct;

    public PurchaseProduct() {
    }

    public PurchaseProduct(int quantity, Product addedProduct) {
        this.quantity = quantity;
        this.matchProduct = addedProduct;
    }

    public String getPurchaseProductID() {
        return purchaseProductID;
    }

    public void setPurchaseProductID(String purchaseProductID) {
        this.purchaseProductID = purchaseProductID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getMatchProduct() {
        return matchProduct;
    }

    public void setMatchProduct(Product addedProduct) {
        this.matchProduct = addedProduct;
    }

    public String getpurchaseProductID() {
        return purchaseProductID;
    }

    public void setpurchaseProductID(String id) {
        this.purchaseProductID = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseProductID != null ? purchaseProductID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseProduct)) {
            return false;
        }
        PurchaseProduct other = (PurchaseProduct) object;
        return this.matchProduct != null && this.matchProduct.getProductID().equals(other.matchProduct.getProductID());

    }

    @Override
    public String toString() {
        return this.matchProduct.getProductName() + " : " + this.quantity;
    }

}
