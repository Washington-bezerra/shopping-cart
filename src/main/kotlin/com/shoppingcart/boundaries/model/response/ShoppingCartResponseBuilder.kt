package com.shoppingcart.boundaries.model.response

import com.shoppingcart.infrastructure.entities.Client
import java.util.*


class ShoppingCartResponseBuilder {
    operator fun invoke(orderId: UUID, client: Client, items: List<ItemResponse>): ShoppingCartResponse{
        var amount = 0.0
        items.forEach {
            amount += it.amount
        }

        return ShoppingCartResponse(
            orderId = orderId,
            client = client,
            items = items,
            amount = amount
        )
    }
}