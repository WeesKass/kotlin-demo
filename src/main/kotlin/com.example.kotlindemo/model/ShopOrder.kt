package com.example.kotlindemo.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class ShopOrder(@Id
                     @GeneratedValue(strategy = GenerationType.IDENTITY)
                     @get: NotNull
                     var orderID: Long,
                     @get: NotBlank
                     var customerData: String,
                     @get: NotBlank
                     var date: String,
                     @get: NotBlank
                     var status: OrderEnumStatus,
                     @get: NotNull
                     var orderPrice: Int) {

}
