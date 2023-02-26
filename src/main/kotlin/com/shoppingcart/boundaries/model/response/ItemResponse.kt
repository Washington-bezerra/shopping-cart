package com.shoppingcart.boundaries.model.response

data class ItemResponse(
    val id: Int,
    val name: String,
    val value: Double,
    val quantity: Int
)