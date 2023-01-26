package com.shoppingcart.application.usecases

import com.shoppingcart.infrastructure.entities.Item
import com.shoppingcart.infrastructure.repositories.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CreateItemsUseCase{

    @Autowired
    lateinit var itemRepository: ItemRepository

    operator fun invoke(items: List<Item>): MutableList<Item> {
        return  itemRepository.saveAll(items)
    }
}