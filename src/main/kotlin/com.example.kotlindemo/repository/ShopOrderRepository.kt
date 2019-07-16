package com.example.kotlindemo.repository

import com.example.kotlindemo.model.ShopOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShopOrderRepository : JpaRepository<ShopOrder, Long>

