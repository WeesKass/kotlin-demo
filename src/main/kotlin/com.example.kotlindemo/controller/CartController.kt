package com.example.kotlindemo.controller

import com.example.kotlindemo.model.Cart
import com.example.kotlindemo.repository.CartRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class CartController(private val cartRepository: CartRepository) {

    @GetMapping("/carts")
    fun getAllCarts(): List<Cart> =
            cartRepository.findAll()


    @PostMapping("/carts")
    fun createNewCart(@Valid @RequestBody cart: Cart): Cart =
            cartRepository.save(cart)


    @GetMapping("/carts/{id}")
    fun getCartById(@PathVariable(value = "id") cartId: Long): ResponseEntity<Cart> {
        return cartRepository.findById(cartId).map { cart ->
            ResponseEntity.ok(cart)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/carts/{id}")
    fun updateCartById(@PathVariable(value = "id") cartId: Long,
                                @Valid @RequestBody newCart: Cart): ResponseEntity<Cart> {

        return cartRepository.findById(cartId).map { existingCart->
            val updatedCart: Cart = existingCart
                    .copy(
                            cartID = newCart.cartID,
                            productID = newCart.productID,
                            quantity= newCart.quantity)

            ResponseEntity.ok().body(cartRepository.save(updatedCart))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/carts/{id}")
    fun deleteCartById(@PathVariable(value = "id") cartId: Long): ResponseEntity<Void> {

        return cartRepository.findById(cartId).map { cart->
            cartRepository.delete(cart)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}
