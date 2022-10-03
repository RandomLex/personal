package com.barzykin.personal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@ToString(callSuper = true, exclude = "divisions")
@EqualsAndHashCode(callSuper = true, exclude = "divisions")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class City extends AbstractEntity {
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @JsonBackReference
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
