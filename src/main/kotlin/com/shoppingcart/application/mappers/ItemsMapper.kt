package com.shoppingcart.application.mappers

import com.shoppingcart.domain.Item
import org.springframework.stereotype.Component
import com.shoppingcart.infrastructure.entities.Item as ItemEntity

@Component
class ItemsMapper : Mapper<List<Item>, List<ItemEntity>> {

    override fun map(items: List<Item>): List<ItemEntity> {
        return items.map {
            item -> ItemEntity(
                name = item.name,
                value = item.value
            )
        }
    }

}