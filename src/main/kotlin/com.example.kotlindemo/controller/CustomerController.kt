package com.example.kotlindemo.controller

import com.example.kotlindemo.model.Customer
import com.example.kotlindemo.repository.CustomerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class CustomerController(private val customerRepository: CustomerRepository) {

    @GetMapping("/customers")
    fun getAllCustomers(): List<Customer> =
            customerRepository.findAll()


    @PostMapping("/customers")
    fun createNewCustomer(@Valid @RequestBody customer: Customer): Customer =
            customerRepository.save(customer)


    @GetMapping("/customers/{id}")
    fun getCustomerById(@PathVariable(value = "id") customerId: Long): ResponseEntity<Customer> {
        return customerRepository.findById(customerId).map { customer ->
            ResponseEntity.ok(customer)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/customers/{id}")
    fun updateCustomerById(@PathVariable(value = "id") customerId: Long,
                       @Valid @RequestBody newCustomer: Customer): ResponseEntity<Customer> {

        return customerRepository.findById(customerId).map { existingCustomer->
            val updatedCustomer: Customer = existingCustomer
                    .copy(
                            userID = newCustomer.userID,
                            password = newCustomer.password,
                            registerData = newCustomer.registerData,
                            email = newCustomer.email,
                            customerName = newCustomer.customerName,
                            address= newCustomer.address)

            ResponseEntity.ok().body(customerRepository.save(updatedCustomer))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/Customers/{id}")
    fun deleteCustomerById(@PathVariable(value = "id") customerId: Long): ResponseEntity<Void> {

        return customerRepository.findById(customerId).map { customer->
            customerRepository.delete(customer)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}
