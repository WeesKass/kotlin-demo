package com.example.kotlindemo.controller

import com.example.kotlindemo.model.Administrator
import com.example.kotlindemo.repository.AdministratorRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class AdministartorController(private val administratorRepository: AdministratorRepository) {

    @GetMapping("/administrators")
    fun getAllAdministrators(): List<Administrator> =
            administratorRepository.findAll()


    @PostMapping("/administrators")
    fun createNewAdministrator(@Valid @RequestBody administrator: Administrator): Administrator
            =
            administratorRepository.save(administrator)


    @GetMapping("/administrators/{id}")
    fun getAdministratorById(@PathVariable(value = "id") administratorId: Long): ResponseEntity<Administrator> {
        return administratorRepository.findById(administratorId).map { administrator ->
            ResponseEntity.ok(administrator)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/administrators/{id}")
    fun updateAdministratorById(@PathVariable(value = "id") administratorId: Long,
                          @Valid @RequestBody newAdministrator: Administrator): ResponseEntity<Administrator> {

        return administratorRepository.findById(administratorId).map { existingAdministrator->
            val updatedAdministrator: Administrator = existingAdministrator
                    .copy(
                            adminID = newAdministrator.adminID,
                            password = newAdministrator.password,
                            registerData = newAdministrator.registerData,
                            email = newAdministrator.email,
                            adminName = newAdministrator.adminName
                    )
            ResponseEntity.ok().body(administratorRepository.save(updatedAdministrator))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/administrators/{id}")
    fun deleteAdministratorById(@PathVariable(value = "id") administratorId: Long): ResponseEntity<Void> {

        return administratorRepository.findById(administratorId).map { administrator ->
            administratorRepository.delete(administrator)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}
