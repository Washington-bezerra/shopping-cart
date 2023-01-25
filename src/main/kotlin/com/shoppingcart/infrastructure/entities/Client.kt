package com.shoppingcart.infrastructure.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "client")
data class Client(
    @Id
    @GeneratedValue
    val id: Int? = null,

    @Column
    val name: String,

    @Column
    val documentNumber: String,

    @Column
    val email: String,

    @Column
    val address: String,

){
    @CreationTimestamp
    @Column
    lateinit var createdAt: LocalDateTime

    @CreationTimestamp
    @Column
    lateinit var updatedAt: LocalDateTime
}