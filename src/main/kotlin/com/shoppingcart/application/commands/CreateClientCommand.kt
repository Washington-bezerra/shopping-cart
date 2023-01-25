package com.shoppingcart.application.commands

data class CreateClientCommand(
    val name: String,
    val documentNumber: String,
    val email: String,
    val address: String,
)
