package com.hackathon.toystore.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductBrand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_brand_id_seq")
    @SequenceGenerator(name = "product_brand_id_seq", sequenceName = "product_brand_id_seq")
    private Long id;
    @NotNull
    private String name;

    public ProductBrand(String name) {
        this.name = name;
    }

}
