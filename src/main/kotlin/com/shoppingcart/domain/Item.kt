package com.shoppingcart.domain

data class Item (
    val name: String,
    val value: Double,
    val status: ItemStatus = ItemStatus.PROCESSING
)
