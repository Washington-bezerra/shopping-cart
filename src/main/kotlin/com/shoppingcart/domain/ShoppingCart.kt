package com.shoppingcart.domain

import com.shoppingcart.domain.request.ItemRequest

data class ShoppingCart(
    val clientId: Int,
    val items: List<ItemRequest>,
)


