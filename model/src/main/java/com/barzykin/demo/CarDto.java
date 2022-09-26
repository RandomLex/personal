package com.barzykin.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
public class CarDto {
    private String name;
    private Date date;
}
