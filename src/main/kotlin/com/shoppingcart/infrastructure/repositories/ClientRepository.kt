package com.shoppingcart.infrastructure.repositories

import com.shoppingcart.infrastructure.entities.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Int>