package com.ecommerce.api.order.application

import com.ecommerce.api.order.application.discount.BulkPurchasesDiscountStrategy
import com.ecommerce.api.order.application.discount.FreePromotionDiscountStrategy
import com.ecommerce.api.order.domain.model.PriceCalculatorService
import com.ecommerce.api.order.domain.model.Product
import com.ecommerce.api.order.domain.model.discount.Discount
import com.ecommerce.api.order.domain.port.DiscountRepository

import spock.lang.Specification
import spock.lang.Subject

class PriceCalculatorServiceTest extends Specification {

    DiscountRepository discountRepository = Mock()

    @Subject
    PriceCalculatorService priceCalculatorService = new DefaultPriceCalculatorService(discountRepository)

    def "calculate price for a product and a quantity"() {
        given:
            Map<String, Discount> discounts = new HashMap<>()
            discounts.put("MARKETING_DISCOUNT", new Discount(true, "STICKER", new FreePromotionDiscountStrategy(2, 2)))
            discounts.put("CUSTOMER_DISCOUNT", new Discount(true, "TSHIRT", new FreePromotionDiscountStrategy(2, 1.5)))
            discounts.put("FINANCIAL_DISCOUNT", new Discount(true, "BOOK", new BulkPurchasesDiscountStrategy(3, new BigDecimal(1))))
            UUID productId = UUID.randomUUID()
            BigDecimal price = new BigDecimal(10)
            Integer quantity = 3
            Product product = new Product(productId, "STICKER", "product", price)
            discountRepository.getAll() >> discounts
        when:
            BigDecimal result = priceCalculatorService.calculate(product, quantity)
        then:
            result == 15
    }

}
