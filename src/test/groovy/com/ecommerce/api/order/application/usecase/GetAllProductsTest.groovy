package com.ecommerce.api.order.application.usecase

import com.ecommerce.api.order.domain.model.Product
import com.ecommerce.api.order.domain.port.OrderRepository
import com.ecommerce.api.order.domain.port.ProductRepository

import spock.lang.Specification
import spock.lang.Subject

class GetAllProductsTest extends Specification {

    ProductRepository repository = Mock()

    @Subject
    GetAllProducts getAllProducts = new GetAllProducts(repository)

    def 'should get all products'() {
        given:
            List<Product> products = new ArrayList<>()
            UUID productId = UUID.randomUUID()
            BigDecimal productPrice = new BigDecimal(12.50)
            Product product = new Product(productId, "BOOK", "newProduct", productPrice)
            products.add(product)
        when:
            List<Product> result = getAllProducts.execute()
        then:
            1 * repository.findAll() >> products
            result.size() > 0
            result.get(0).id() == product.id()
            result.get(0).price() == product.price()
            result.get(0).code() == product.code()
            result.get(0).description() == product.description()
    }

}
