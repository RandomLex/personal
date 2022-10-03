package com.barzykin.demo.hqljoin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "product_type")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "productType", cascade = {CascadeType.PERSIST}/*, fetch = FetchType.EAGER*/ )
    //FetchType.EAGER вытаскивает сразу все данные из присоединённой таблицы, но если их там много
    //то это замедляет работу приложения и занимает большое количество оперативной памяти
    private Set<Product> products;
}
