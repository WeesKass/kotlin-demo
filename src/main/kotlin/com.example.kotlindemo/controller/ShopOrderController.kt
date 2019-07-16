package com.example.kotlindemo.controller

import com.example.kotlindemo.model.ShopOrder
import com.example.kotlindemo.repository.ShopOrderRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class ShopOrderController(private val shopOrderRepository: ShopOrderRepository) {

    @GetMapping("/shopOrders")
    fun getAllOrders(): List<ShopOrder> =
            shopOrderRepository.findAll()


    @PostMapping("/shopOrders")
    fun createNewOrder(@Valid @RequestBody shopOrder: ShopOrder): ShopOrder =
            shopOrderRepository.save(shopOrder)


    @GetMapping("/shopOrders/{id}")
    fun getShopOrderById(@PathVariable(value = "id") shopOrderId: Long): ResponseEntity<ShopOrder> {
        return shopOrderRepository.findById(shopOrderId).map { shopOrder ->
            ResponseEntity.ok(shopOrder)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/shopOrders/{id}")
    fun updateShopOrderById(@PathVariable(value = "id") shopOrderId: Long,
                       @Valid @RequestBody newShopOrder: ShopOrder): ResponseEntity<ShopOrder> {

        return shopOrderRepository.findById(shopOrderId).map { existingShopOrder->
            val updatedShopOrder: ShopOrder = existingShopOrder
                    .copy(
                            orderID = newShopOrder.orderID,
                            customerData = newShopOrder.customerData,
                            date = newShopOrder.date,
                            status = newShopOrder.status,
                            orderPrice = newShopOrder.orderPrice)

            ResponseEntity.ok().body(shopOrderRepository.save(updatedShopOrder))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/shopOrders/{id}")
    fun deleteShopOrderById(@PathVariable(value = "id") shopOrderId: Long): ResponseEntity<Void> {

        return shopOrderRepository.findById(shopOrderId).map { shopOrder->
            shopOrderRepository.delete(shopOrder)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}
