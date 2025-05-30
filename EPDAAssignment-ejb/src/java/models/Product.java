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

/**
 *
 * @author wengk
 */
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String productID ;
    private String productName;
    private double price;
    private int quantity;
    private boolean isForSold;

    public Product() {
    }

    public Product(String productID, String productName, double price, int quantity, boolean isForSold) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.isForSold = isForSold;
    }

    public Product(String productName, double price, int quantity, boolean isForSold) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.isForSold = isForSold;
    }
    
    

    public boolean isIsForSold() {
        return isForSold;
    }

    public void setIsForSold(boolean isForSold) {
        this.isForSold = isForSold;
    }

    public Product(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String id) {
        this.productID = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productID != null ? productID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productID == null && other.productID != null) || (this.productID != null && !this.productID.equals(other.productID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.productName + " : " + this.quantity;
    }

}
