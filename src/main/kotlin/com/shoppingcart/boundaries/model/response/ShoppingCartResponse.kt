package com.shoppingcart.boundaries.model.response

import com.shoppingcart.infrastructure.entities.Client
import java.util.*

data class ShoppingCartResponse(
    val orderId: UUID,
    val client: Client,
    val items: List<ItemResponse>
)