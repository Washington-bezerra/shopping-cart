package com.shoppingcart.boundaries.model.request

import com.shoppingcart.domain.Item
data class CreateItemsRequest(
    val items: List<Item>
)
