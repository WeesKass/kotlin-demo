package com.example.kotlindemo.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class Administrator(@Id
                         @GeneratedValue(strategy = GenerationType.IDENTITY)
                         @get: NotNull
                         var adminID: Long,
                         @get: NotBlank
                         var password: String,
                         @get: NotBlank
                         var registerData: String,
                         @get: NotBlank
                         var email: String,
                         @get: NotBlank
                         var adminName: String) {
}