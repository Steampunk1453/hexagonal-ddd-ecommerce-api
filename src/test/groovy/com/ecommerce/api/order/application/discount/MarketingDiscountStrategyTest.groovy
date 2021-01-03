package com.ecommerce.api.order.application.discount

import org.javamoney.moneta.Money

import com.ecommerce.api.order.domain.model.Product

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class MarketingDiscountStrategyTest extends Specification {

    @Subject
    MarketingDiscountStrategy marketingDiscountStrategy = new MarketingDiscountStrategy()

    @Unroll
    def "should apply a discount of 2x1 with result #expect for product #productCode and quantity #quantity with price #price"() {
        given:
            UUID productId = UUID.randomUUID()
            Money value = Money.of(new BigDecimal(price), "EUR")
            Product product = new Product(productId, productCode, "description", value)
        when:
            BigDecimal result = marketingDiscountStrategy.apply(product, quantity)
        then:
            result == expect
        where:
            quantity | price | productCode || expect
            1        | 2.50  | "STICKER"   || 2.50
            2        | 2.50  | "STICKER"   || 2.50
            3        | 2.50  | "STICKER"   || 5.00
            4        | 2.50  | "STICKER"   || 5.00
            5        | 2.50  | "STICKER"   || 7.50
            6        | 2.50  | "STICKER"   || 7.50
            2        | 15.00 | "TSHIRT"    || 30.00
            4        | 20.00 | "BOOK"      || 80.00
    }

}
