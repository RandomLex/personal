package com.barzykin.demo.complexid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Embeddable // переводится, как "встраиваемый". Аннотация для создания класса, являющегося
//составным идентификатором
public class EmpId implements Serializable { // Составной идинтификатор обязан имплементировать Serializable
    private String lastname;
    private String name;
}
