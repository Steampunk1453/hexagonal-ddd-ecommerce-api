package com.ecommerce.api.order.application.discount

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

    def "should apply discount of bulk Purchases with result #expect for product BOOK and quantity #quantity with price #price"() {
    }
}
