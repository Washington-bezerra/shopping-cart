package com.shoppingcart.boundaries.model

import com.shoppingcart.domain.Item
data class CreateItemsRequest(
    val items: List<Item>
)
