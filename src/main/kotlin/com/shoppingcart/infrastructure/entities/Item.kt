package com.shoppingcart.infrastructure.entities

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.shoppingcart.domain.ItemStatus
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
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
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    lateinit var createdAt: LocalDateTime

    @UpdateTimestamp
    @Column
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    lateinit var updatedAt: LocalDateTime
}