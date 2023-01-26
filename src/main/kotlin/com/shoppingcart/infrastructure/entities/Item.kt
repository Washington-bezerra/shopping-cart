package com.shoppingcart.infrastructure.entities

import com.shoppingcart.domain.ItemStatus
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "item")
data class Item(
    @Id
    @GeneratedValue
    val id: Int? = null,

    @Column
    val name: String,

    @Column
    val value: Double,

    @Enumerated(EnumType.STRING)
    @Column
    val status: ItemStatus = ItemStatus.PROCESSING,

    ){
    @CreationTimestamp
    @Column
    lateinit var createdAt: LocalDateTime

    @CreationTimestamp
    @Column
    lateinit var updatedAt: LocalDateTime
}