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
public enum OrderStatus {
    ORDERRECEIVED,
    PREPARING,
    READYFORDELIVERY,
    DELIVERING,
    DELIVERED,
    RATED;

    @Override
    public String toString() {
        switch (this) {
            case ORDERRECEIVED:
                return "Order Received";
            case PREPARING:
                return "Preparing";
            case READYFORDELIVERY:
                return "Ready for Delivery";
            case DELIVERING:
                return "Delivering";
            case DELIVERED:
                return "Delivered";
            case RATED:
                return "Rated";
            default:
                return super.toString();  // Fallback for safety
        }
    }
}
