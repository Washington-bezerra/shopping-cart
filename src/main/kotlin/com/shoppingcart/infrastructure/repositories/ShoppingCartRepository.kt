package com.shoppingcart.infrastructure.repositories

import com.shoppingcart.infrastructure.entities.ShoppingCart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@EnableJpaRepositories
interface ShoppingCartRepository : JpaRepository<ShoppingCart, Int> {
    fun findByOrderId(orderId: UUID): ShoppingCart
}