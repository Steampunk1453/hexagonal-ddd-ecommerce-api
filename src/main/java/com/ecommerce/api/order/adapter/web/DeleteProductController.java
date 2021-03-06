package com.ecommerce.api.order.adapter.web;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.order.application.usecase.DeleteProduct;

@RestController
public class DeleteProductController {

    private final DeleteProduct deleteProduct;

    public DeleteProductController(DeleteProduct deleteProduct) {
        this.deleteProduct = deleteProduct;
    }

    @DeleteMapping("/products/{orderId}/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("orderId") final UUID orderId, @PathVariable("productId") final UUID productId) {
        deleteProduct.execute(orderId, productId);
    }

}
