package com.example.kotlindemo.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class ShippingInfo (@Id
                         @GeneratedValue(strategy = GenerationType.IDENTITY)
                         @get: NotNull
                         var shippingID: Long,
                         @get: NotBlank
                         var shippingType: ShippingTypeEnum,
                         @get: NotNull
                         var shippingCost: Double){

}