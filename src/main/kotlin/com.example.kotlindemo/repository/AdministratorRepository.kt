package com.example.kotlindemo.repository

import com.example.kotlindemo.model.Administrator
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdministratorRepository : JpaRepository<Administrator, Long>
