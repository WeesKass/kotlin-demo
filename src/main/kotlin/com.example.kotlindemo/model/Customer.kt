package com.example.kotlindemo.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class Customer (@Id
                     @GeneratedValue(strategy = GenerationType.IDENTITY)
                     @get: NotNull
                     var userID: Long,
                     @get: NotBlank
                     var password: String,
                     @get: NotBlank
                     var registerData: String,
                     @get: NotBlank
                     var email: String,
                     @get: NotBlank
                     var customerName: String,
                     @get: NotBlank
                     var address: String){
}