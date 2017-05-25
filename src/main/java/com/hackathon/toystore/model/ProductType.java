package com.hackathon.toystore.model;

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
public class ProductType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_type_id_seq")
    @SequenceGenerator(name = "product_type_id_seq", sequenceName = "product_type_id_seq")
    private Long id;
    @NotNull
    private String name;

}
