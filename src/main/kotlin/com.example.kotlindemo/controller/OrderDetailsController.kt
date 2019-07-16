package com.example.kotlindemo.controller

import com.example.kotlindemo.model.OrderDetails
import com.example.kotlindemo.repository.OrderDetailsRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class OrderDetailsController(private val orderDetailsRepository: OrderDetailsRepository) {

    @GetMapping("/orderDetailss")
    fun getAllOrderDetails(): List<OrderDetails> =
            orderDetailsRepository.findAll()


    @PostMapping("/orderDetailss")
    fun createNewOrder(@Valid @RequestBody orderDetails: OrderDetails): OrderDetails =
            orderDetailsRepository.save(orderDetails)


    @GetMapping("/orderDetailss/{id}")
    fun getOrderDetailsById(@PathVariable(value = "id") orderDetailsId: Long): ResponseEntity<OrderDetails> {
        return orderDetailsRepository.findById(orderDetailsId).map { orderDetails ->
            ResponseEntity.ok(orderDetails)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/orderDetailss/{id}")
    fun updateOrderDetailsById(@PathVariable(value = "id") orderDetailsId: Long,
                            @Valid @RequestBody newOrderDetails: OrderDetails): ResponseEntity<OrderDetails> {

        return orderDetailsRepository.findById(orderDetailsId).map { existingOrderDetails->
            val updatedOrderDetails: OrderDetails = existingOrderDetails
                    .copy(
                            orderID = newOrderDetails.orderID,
                            productID = newOrderDetails.productID,
                            productName = newOrderDetails.productName,
                            quantity = newOrderDetails.quantity,
                            unitCost = newOrderDetails.unitCost,
                            subTotal = newOrderDetails.subTotal)

            ResponseEntity.ok().body(orderDetailsRepository.save(updatedOrderDetails))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/orderDetailss/{id}")
    fun deleteOrderDetailsById(@PathVariable(value = "id") orderDetailsId: Long): ResponseEntity<Void> {

        return orderDetailsRepository.findById(orderDetailsId).map { orderDetails->
            orderDetailsRepository.delete(orderDetails)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}
