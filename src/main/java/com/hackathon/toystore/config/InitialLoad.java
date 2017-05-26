package com.hackathon.toystore.config;

import com.hackathon.toystore.model.Product;
import com.hackathon.toystore.model.ProductBrand;
import com.hackathon.toystore.repository.ProductRepository;
import com.hackathon.toystore.repository.ProductBrandRepository;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InitialLoad {

    private final ProductBrandRepository productBrandRepository;
    private final ProductRepository productRepository;

    @PostConstruct
    public void loadDatabase() {
        final ProductBrand boeing = productBrandRepository.save(new ProductBrand("Boeing"));
        final ProductBrand airbus = productBrandRepository.save(new ProductBrand("Airbus"));
        final ProductBrand embraer = productBrandRepository.save(new ProductBrand("Embraer"));

        productRepository.save(new Product("747 8",boeing, BigDecimal.valueOf(351.4)));
        productRepository.save(new Product("777 300ER",boeing, BigDecimal.valueOf(315)));
        productRepository.save(new Product("737 600",boeing, BigDecimal.valueOf(57)));

        productRepository.save(new Product("A330-200",airbus, BigDecimal.valueOf(231.5)));
        productRepository.save(new Product("A319",airbus, BigDecimal.valueOf(89.6)));
        productRepository.save(new Product("A320neo",airbus, BigDecimal.valueOf(107.3)));

        productRepository.save(new Product("E-175",embraer, BigDecimal.valueOf(45)));
        productRepository.save(new Product("E-190",embraer, BigDecimal.valueOf(49.8)));
        productRepository.save(new Product("E-195",embraer, BigDecimal.valueOf(52.7)));
    }

}
