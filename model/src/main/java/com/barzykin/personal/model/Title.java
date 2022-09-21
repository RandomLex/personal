package com.barzykin.personal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Title extends AbstractEntity {
    private String name;

    public Title withId(Long id) {
        this.id = id;
        return this;
    }

    public Title withName(String name) {
        this.name = name;
        return this;
    }
}
