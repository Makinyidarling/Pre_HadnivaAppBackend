package com.Hadniva.hadnivaBackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
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
}
