package com.barzykin.personal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class City extends AbstractEntity {
    private String name;
    private List<Division> divisions;

    public City withId(Long id) {
        this.id = id;
        return this;
    }

    public City withName(String name) {
        this.name = name;
        return this;
    }
}
