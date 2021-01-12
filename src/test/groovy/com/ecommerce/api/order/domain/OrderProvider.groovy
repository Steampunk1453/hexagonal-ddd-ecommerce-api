package com.ecommerce.api.order.domain

import com.ecommerce.api.order.domain.model.Order
import com.ecommerce.api.order.domain.model.Product

class OrderProvider {

    static Order buildOrder() {
        UUID orderId = UUID.randomUUID()
        UUID productId = UUID.randomUUID()
        BigDecimal price = new BigDecimal(2.50)
        Product product = new Product(productId, "STICKER", "product", price)
        Integer productQuantity = 1
        BigDecimal itemPrice = new BigDecimal(2.50)
        new Order(orderId, product, productQuantity, itemPrice)
    }

}
