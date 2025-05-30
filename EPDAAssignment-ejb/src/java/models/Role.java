/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author wengk
 */
public enum Role {
    MANAGINGSTAFF,
    CUSTOMER,
    DELIVERYSTAFF;

    @Override
    public String toString() {
        switch (this) {
            case MANAGINGSTAFF:
                return "Managing Staff";
            case CUSTOMER:
                return "Customer";
            case DELIVERYSTAFF:
                return "Delivery Staff";
            default:
                throw new IllegalArgumentException("Unexpected value: " + this);
        }
    }
}
