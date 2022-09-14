package com.barzykin.personal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Data
@ToString(callSuper = true, exclude = "divisions")
@EqualsAndHashCode(callSuper = true, exclude = "divisions")
@NoArgsConstructor
@AllArgsConstructor
public class City extends AbstractEntity {
    private String name;
    private Set<Division> divisions;

    public City withId(Long id) {
        this.id = id;
        return this;
    }

    public City withName(String name) {
        this.name = name;
        return this;
    }
}
