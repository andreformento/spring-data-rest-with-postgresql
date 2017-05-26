package com.hackathon.toystore.repository;

import com.hackathon.toystore.model.ProductBrand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductBrandRepository extends CrudRepository<ProductBrand, Long> {

    Iterable<ProductBrand> findByName(@Param("name") final String name);

}
