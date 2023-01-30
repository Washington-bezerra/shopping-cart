package com.shoppingcart.application.controllers
import com.shoppingcart.application.mappers.ItemsMapper
import com.shoppingcart.application.model.request.CreateItemsRequest
import com.shoppingcart.application.usecases.item.CreateItemsUseCase
import com.shoppingcart.application.usecases.item.PutItemUseCase
import com.shoppingcart.domain.ItemStatus
import com.shoppingcart.infrastructure.entities.Item
import org.slf4j.LoggerFactory
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ItemController(
    private val createItemsUseCase: CreateItemsUseCase,
    private val putItemUseCase: PutItemUseCase,
    private val itemsMapper: ItemsMapper
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/item")
    fun createItems(@RequestBody itemsRequest: CreateItemsRequest): MutableList<Item> {
        log.info("Receiving items request")
        return createItemsUseCase(itemsMapper.map(itemsRequest.items))
    }

    @PutMapping("/item/{itemId}")
    fun putItem(
        @PathVariable itemId: Int,
        @Param("status") status: ItemStatus
    ): ResponseEntity<*> {
        log.info("Received put request for item {}", itemId)
        return putItemUseCase(itemId, status)
    }
}