package com.ecommerce.api.order.application.discount

import com.ecommerce.api.order.domain.model.Product

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class BulkPurchasesDiscountStrategyTest extends Specification {

    @Subject
    BulkPurchasesDiscountStrategy bulkPurchasesDiscountStrategy

    @Unroll
    def "should return #isEligible if #quantity is greater or equal than #minimumQuantity"() {
        given:
            bulkPurchasesDiscountStrategy = new BulkPurchasesDiscountStrategy(minimumQuantity, 1)
        when:
            boolean result = bulkPurchasesDiscountStrategy.isEligible(quantity)
        then:
            result == isEligible
        where:
            quantity | minimumQuantity || isEligible
            1        | 2               || false
            2        | 2               || true
            3        | 2               || true
    }

    def "should apply discount of bulk purchases with result #expect for product BOOK and quantity #quantity with price #price and discounted applied #discountedApplied "() {
        given:
            bulkPurchasesDiscountStrategy = new BulkPurchasesDiscountStrategy(3, discountedApplied)
            UUID productId = UUID.randomUUID()
            Product product = new Product(productId, "BOOK", "description", price)
        when:
            BigDecimal result = bulkPurchasesDiscountStrategy.apply(product, quantity)
        then:
            result == expect
        where:
            quantity | price | discountedApplied || expect
            3        | 12.00 | 1.00              || 33.00
            3        | 12.00 | 2.50              || 28.50
            3        | 12.50 | 3.50              || 27.00
            4        | 10.00 | 1.00              || 36.00
            5        | 11.00 | 2.50              || 42.50
            6        | 15.00 | 3.50              || 69.00
    }
}
