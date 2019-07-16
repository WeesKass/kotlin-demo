package com.example.kotlindemo.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class Cart(@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                @get: NotNull
                var cartID: Long,
                @get: NotNull
                var productID: Long,
                @get: NotNull
                var quantity: Int) {

}