package com.example.kotlindemo.repository

import com.example.kotlindemo.model.ShippingInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShippingInfoRepository : JpaRepository<ShippingInfo, Long>
