package com.Hadniva.hadnivaBackend.dto;

import java.util.Date;

public class ChartDataDTO {
    private String userName;
    private String serviceName;
    private Date date;
    private Double price;

    public ChartDataDTO(String userName, String serviceName, Date localDateTime, Double price) {
        this.userName = userName;
        this.serviceName = serviceName;
        this.date = localDateTime;
        this.price = price;
    }

    // Getters and Setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
