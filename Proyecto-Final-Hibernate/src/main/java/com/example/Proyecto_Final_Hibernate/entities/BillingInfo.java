package com.example.Proyecto_Final_Hibernate.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "billing_info")
@JsonIgnoreProperties({"user"})
public class BillingInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "billing_info_id")
    private Long id;
    private String adress;
    @Column(name = "postal_code")
    private String postalCode;
    private String city;
    private String province;
    private String country;
    @Column(name = "account_number", unique = true)
    private String accountNumber;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public BillingInfo() {
    }


    public BillingInfo(Long id, String adress, String postalCode, String city, String province, String country, String accountNumber, User user) {
        this.id = id;
        this.adress = adress;
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
        this.country = country;
        this.accountNumber = accountNumber;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null && user.getBillingInfo() != this) {
            user.setBillingInfo(this);
        }
    }


    @Override
    public String toString() {
        return "BillingInfo{" +
                "id=" + id +
                ", adress='" + adress + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }




}
