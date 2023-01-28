package com.shoppingcart.application.controllers
import com.shoppingcart.application.mappers.ItemsMapper
import com.shoppingcart.application.model.request.CreateItemsRequest
import com.shoppingcart.application.usecases.CreateItemsUseCase
import com.shoppingcart.infrastructure.entities.Item
import org.slf4j.LoggerFactory
import com.shoppingcart.infrastructure.entities.Item as ItemEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController(
    private val createItemsUseCase: CreateItemsUseCase,
    private val itemsMapper: ItemsMapper
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/item")
    fun createItems(@RequestBody itemsRequest: CreateItemsRequest): MutableList<Item> {

        log.info("Receiving items request")

        return createItemsUseCase(itemsMapper.map(itemsRequest.items))
    }
}