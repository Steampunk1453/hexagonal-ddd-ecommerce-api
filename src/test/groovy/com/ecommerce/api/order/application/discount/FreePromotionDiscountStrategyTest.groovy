package com.ecommerce.api.order.application.discount

import com.ecommerce.api.order.domain.model.Product

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class FreePromotionDiscountStrategyTest extends Specification {

    @Subject
    FreePromotionDiscountStrategy marketingDiscountStrategy

    @Unroll
    def "should return #isEligible if #quantity is greater or equal than #minimumQuantity"() {
        given:
            marketingDiscountStrategy = new FreePromotionDiscountStrategy(minimumQuantity, 2)
        when:
            boolean result = marketingDiscountStrategy.isEligible(quantity)
        then:
            result == isEligible
        where:
            quantity | minimumQuantity || isEligible
            1        | 2               || false
            2        | 2               || true
            3        | 2               || true
    }

    @Unroll
    def "should apply a discount of free promotion with result #expect for product STICKER and quantity #quantity with price #price and divider #divider"() {
        given:
            marketingDiscountStrategy = new FreePromotionDiscountStrategy(2, divider)
            UUID productId = UUID.randomUUID()
            Product product = new Product(productId, "STICKER", "description", price)
        when:
            BigDecimal result = marketingDiscountStrategy.apply(product, quantity)
        then:
            result == expect
        where:
            quantity | price | divider || expect
            2        | 2.50  | 2.00    || 2.50
            3        | 2.50  | 2.00    || 3.75
            4        | 2.50  | 2.00    || 5.00
            5        | 2.50  | 1.50    || 8.35
            6        | 2.50  | 2.00    || 7.50
            3        | 5.00  | 1.50    || 10.00
            4        | 10.00 | 2.00    || 20.00
    }

}
