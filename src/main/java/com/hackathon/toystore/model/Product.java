package com.hackathon.toystore.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq")
    private Long id;
    @NotNull
    private String description;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ProductBrand productBrand;
    private BigDecimal price;

    public Product(String description, ProductBrand productBrand, BigDecimal price) {
        this.description = description;
        this.productBrand = productBrand;
        this.price = price;
    }
}
