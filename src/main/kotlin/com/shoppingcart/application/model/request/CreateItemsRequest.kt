package com.shoppingcart.application.model.request

import com.shoppingcart.domain.Item
data class CreateItemsRequest(
    val items: List<Item>
)
