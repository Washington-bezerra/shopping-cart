package com.shoppingcart.application.controllers
import com.shoppingcart.application.mappers.ItemsMapper
import com.shoppingcart.application.model.request.CreateItemsRequest
import com.shoppingcart.application.usecases.CreateItemsUseCase
import com.shoppingcart.infrastructure.entities.Item
import com.shoppingcart.infrastructure.entities.Item as ItemEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController(
    private val createItemsUseCase: CreateItemsUseCase,
    private val itemsMapper: ItemsMapper
) {


    @PostMapping("/item")
    fun createItems(@RequestBody itemsRequest: CreateItemsRequest): MutableList<Item> {
        return createItemsUseCase(itemsMapper.map(itemsRequest.items))
    }
}