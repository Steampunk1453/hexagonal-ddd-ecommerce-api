package com.ecommerce.api.order.application.discount

import com.ecommerce.api.order.domain.model.Product
import com.ecommerce.api.order.domain.model.discount.Discount
import com.ecommerce.api.order.domain.model.discount.PriceCalculatorService
import com.ecommerce.api.order.domain.port.DiscountRepository

import spock.lang.Specification
import spock.lang.Subject

class PriceCalculatorServiceTest extends Specification {

    DiscountRepository discountRepository = Mock()

    @Subject
    PriceCalculatorService priceCalculatorService = new DefaultPriceCalculatorService(discountRepository)

    def "should calculate price with result #expect for a #productCode with quantity #quantity, price #price and discount is active #isActive"() {
        given:
            Map<String, Discount> discounts = new HashMap<>()
            discounts.put("MARKETING_DISCOUNT", new Discount(isActive, "STICKER", new FreePromotionDiscountStrategy(2, 2)))
            discounts.put("CUSTOMER_DISCOUNT", new Discount(isActive, "BOOK", new FreePromotionDiscountStrategy(2, 1.5)))
            discounts.put("FINANCIAL_DISCOUNT", new Discount(isActive, "BOOK", new BulkPurchasesDiscountStrategy(3, new BigDecimal(1))))
            discounts.put("AFFILIATE_DISCOUNT", new Discount(isActive, "TSHIRT", new BulkPurchasesDiscountStrategy(3, new BigDecimal(2))))
            UUID productId = UUID.randomUUID()
            Product product = new Product(productId, productCode, "product", price)
            discountRepository.getAll() >> discounts
        when:
            BigDecimal result = priceCalculatorService.calculate(product, quantity)
        then:
            result == expect
        where:
            quantity | price | productCode | isActive || expect
            1        | 2.50  | "STICKER"   | true     || 2.50
            2        | 2.50  | "STICKER"   | true     || 2.50
            3        | 12.50 | "BOOK"      | true     || 25.00
            4        | 10.00 | "TSHIRT"    | true     || 32.00
            5        | 2.50  | "STICKER"   | false    || 12.50
            6        | 3.00  | "OTHER"     | true     || 18.00
    }

}
