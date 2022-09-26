package com.barzykin.demo.complexid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Emp {
    @EmbeddedId //Аннотация составного идентификатора
    private EmpId empId;
    private int salary;

}
