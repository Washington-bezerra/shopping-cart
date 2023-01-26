package com.shoppingcart.application.commands

data class CreateItemsCommand(
    val name: String,
    val value: Double,
)
