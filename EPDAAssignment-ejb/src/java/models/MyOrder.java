/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author wengk
 */
@Entity
public class MyOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String orderID;
    @OneToOne
    private MyUser customer;
    private OrderStatus orderStatus;
    private Date orderDate;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private ArrayList<PurchaseProduct> products;

    public MyOrder() {
    }

    public MyOrder(MyUser customer, OrderStatus orderStatus, Date orderDate, ArrayList<PurchaseProduct> products) {
        this.customer = customer;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.products = products;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public MyUser getCustomer() {
        return customer;
    }

    public void setCustomer(MyUser customer) {
        this.customer = customer;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<PurchaseProduct> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<PurchaseProduct> products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderID != null ? orderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MyOrder)) {
            return false;
        }
        MyOrder other = (MyOrder) object;
        if ((this.orderID == null && other.orderID != null) || (this.orderID != null && !this.orderID.equals(other.orderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return products.toString();
    }

}
