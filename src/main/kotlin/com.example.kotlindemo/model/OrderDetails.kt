package com.example.kotlindemo.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class OrderDetails(@Id
                        @GeneratedValue(strategy = GenerationType.IDENTITY)
                        @get: NotNull
                        var orderID: Long,
                        @get: NotBlank
                        var productID: Long,
                        @get: NotBlank
                        var productName: String,
                        @get: NotNull
                        var quantity: Int,
                        @get: NotNull
                        var unitCost: Double,
                        @get: NotNull
                        var subTotal: Double) {

}