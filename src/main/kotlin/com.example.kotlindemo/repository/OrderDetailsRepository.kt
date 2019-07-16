package com.example.kotlindemo.repository

import com.example.kotlindemo.model.OrderDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderDetailsRepository : JpaRepository<OrderDetails, Long>
