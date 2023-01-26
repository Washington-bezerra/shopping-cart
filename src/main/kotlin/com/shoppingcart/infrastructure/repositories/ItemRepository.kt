package com.shoppingcart.infrastructure.repositories

import com.shoppingcart.infrastructure.entities.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<Item, Int>