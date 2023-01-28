package com.shoppingcart.infrastructure.repositories

import com.shoppingcart.domain.ItemStatus
import com.shoppingcart.infrastructure.entities.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Repository
@EnableJpaRepositories
interface ItemRepository : JpaRepository<Item, Int> {

    @Query(
        value = "UPDATE item SET status = :status WHERE id = :id",
        nativeQuery = true
    )
    @Modifying
    @Transactional
    fun updateStatusById(status: String, id: Int)
}