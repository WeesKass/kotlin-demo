package com.example.kotlindemo.controller


import com.example.kotlindemo.model.ShippingInfo
import com.example.kotlindemo.repository.ShippingInfoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class ShippingInfoController(private val shippingInfoRepository: ShippingInfoRepository) {

    @GetMapping("/shippingInfos")
    fun getAllShippingInfos(): List<ShippingInfo> =
            shippingInfoRepository.findAll()


    @PostMapping("/shippingInfos")
    fun createNewShippingInfo(@Valid @RequestBody shippingInfo: ShippingInfo): ShippingInfo =
            shippingInfoRepository.save(shippingInfo)


    @GetMapping("/shippingInfos/{id}")
    fun getShippingInfoById(@PathVariable(value = "id") shippingInfoId: Long): ResponseEntity<ShippingInfo> {
        return shippingInfoRepository.findById(shippingInfoId).map { shippingInfo ->
            ResponseEntity.ok(shippingInfo)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/shippingInfos/{id}")
    fun updateShippingInfoById(@PathVariable(value = "id") shippingInfoId: Long,
                       @Valid @RequestBody newShippingInfo: ShippingInfo): ResponseEntity<ShippingInfo> {

        return shippingInfoRepository.findById(shippingInfoId).map { existingShippingInfo->
            val updatedShippingInfo: ShippingInfo = existingShippingInfo
                    .copy(
                            shippingID = newShippingInfo.shippingID,
                            shippingType = newShippingInfo.shippingType,
                            shippingCost= newShippingInfo.shippingCost)

            ResponseEntity.ok().body(shippingInfoRepository.save(updatedShippingInfo))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/shippingInfos/{id}")
    fun deleteShippingInfoById(@PathVariable(value = "id") shippingInfoId: Long): ResponseEntity<Void> {

        return shippingInfoRepository.findById(shippingInfoId).map { shippingInfo->
            shippingInfoRepository.delete(shippingInfo)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}
