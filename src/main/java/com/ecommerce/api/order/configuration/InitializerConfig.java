package com.ecommerce.api.order.configuration;

import java.math.BigDecimal;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.api.order.application.discount.BulkPurchasesDiscountStrategy;
import com.ecommerce.api.order.application.discount.FreePromotionDiscountStrategy;
import com.ecommerce.api.order.domain.model.Product;
import com.ecommerce.api.order.domain.model.discount.Discount;
import com.ecommerce.api.order.domain.port.DiscountRepository;
import com.ecommerce.api.order.domain.port.ProductRepository;

@Configuration
public class InitializerConfig {

    private static final Logger LOG = LoggerFactory.getLogger(InitializerConfig.class);

    @Bean
    public ApplicationRunner productInitializer(ProductRepository repository) {
        var sticker = new Product(UUID.randomUUID(), "STICKER", "Sticker with technology logo", new BigDecimal("2.50"));
        LOG.info("Sticker Id: {}", sticker.id());
        var tshirt = new Product(UUID.randomUUID(), "TSHIRT", "T-shirt with technology logo", new BigDecimal("10.00"));
        LOG.info("T-Shirt Id: {}", tshirt.id());
        var book = new Product(UUID.randomUUID(), "BOOK", "Book about programming", new BigDecimal("12.50"));
        LOG.info("Book Id: {}", book.id());
        return args -> {
            repository.save(sticker);
            repository.save(tshirt);
            repository.save(book);
        };
    }

    @Bean
    public ApplicationRunner discountInitializer(DiscountRepository repository) {
        var marketingDiscount = new Discount(true, "STICKER", new FreePromotionDiscountStrategy(2, new BigDecimal(2)));
        var financialDiscount = new Discount(true, "BOOK", new BulkPurchasesDiscountStrategy(3, new BigDecimal(1)));
        return args -> {
            repository.save("MARKETING_DISCOUNT", marketingDiscount);
            repository.save("FINANCIAL_DISCOUNT", financialDiscount);
        };
    }

}
