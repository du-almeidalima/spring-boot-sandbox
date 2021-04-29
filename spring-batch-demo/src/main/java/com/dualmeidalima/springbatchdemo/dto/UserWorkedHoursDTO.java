package com.dualmeidalima.springbatchdemo.dto;

import lombok.Data;

@Data
public class UserWorkedHoursDTO {
    private Integer id;
    private String name;
    private Integer hoursWorked;
}
