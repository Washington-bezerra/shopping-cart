package com.shoppingcart.infrastructure.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
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

){
    @CreationTimestamp
    @Column
    lateinit var createdAt: LocalDateTime

    @CreationTimestamp
    @Column
    lateinit var updatedAt: LocalDateTime
}