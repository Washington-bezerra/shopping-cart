package com.shoppingcart.domain

data class Item (
    val id: Int,
    val name: String,
    val value: Double,
    val quantity: Int,
    val amount: Double
)
