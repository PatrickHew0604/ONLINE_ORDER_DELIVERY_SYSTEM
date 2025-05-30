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
public enum Gender {
    MALE, FEMALE;

    @Override
    public String toString() {
        switch (this) {
            case MALE:
                return "Male";
            case FEMALE:
                return "Female";
            default:
                throw new IllegalArgumentException("Unexpected value: " + this);
        }
    }
}
