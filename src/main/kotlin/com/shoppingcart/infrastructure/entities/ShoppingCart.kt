package com.shoppingcart.infrastructure.entities

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "shopping_cart")
data class ShoppingCart(
    @Id
    @GeneratedValue
    val id: Int? = null,

    @Column(name = "order_id")
    val orderId: UUID,

    @ManyToOne
    @JoinColumn(name = "client_id")
    val client: Client,

    @ManyToOne
    @JoinColumn(name = "item_id")
    val item: Item,

    @Column(name = "quantity_item")
    val quantityItem: Int,

){
    @CreationTimestamp
    @Column
    lateinit var createdAt: LocalDateTime

    @UpdateTimestamp
    @Column
    lateinit var updatedAt: LocalDateTime
}