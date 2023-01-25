package com.shoppingcart.domain

data class ShoppingCart(
    val client: Client,
    val items: List<Item>,
    val amount: Double,
)
