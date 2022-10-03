package com.barzykin.demo.hqljoin;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@NamedQuery(name = "byPriceGreaterThan", query = "select new com.barzykin.demo.hqljoin.ProductDto(p.name, p.price) from Product p where p.price > :priceGreaterThan")
@NamedQuery(name = "byProductName", query = "select new com.barzykin.demo.hqljoin.ProductDto(p.name, p.price) from Product p where p.name = :name")
@NamedQuery(name = "byNameAndPriceLessOrEqualsThan", query = "select new com.barzykin.demo.hqljoin.ProductDto(p.name, p.price) from Product p where p.name = :name and p.price <= :price")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int price;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ProductType productType;
}
