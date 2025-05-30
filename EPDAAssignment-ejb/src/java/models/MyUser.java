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
public class MyUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;
    private String name;
    private String email;
    private String password;
    private Gender gender;
    private String phoneNumber;
    private String identityCardNumber;
    private String address;
    private Role role;
    private boolean isAvailable;


    public MyUser(String username, String password, Role role, String name, String email, Gender gender, String phoneNumber, String identityCardNumber, String address,  boolean isAvailable){
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.identityCardNumber = identityCardNumber;
        this.address = address;
        this.role = role;
        this.isAvailable = isAvailable;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MyUser() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MyUser)) {
            return false;
        }
        MyUser other = (MyUser) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return username;
    }

}
