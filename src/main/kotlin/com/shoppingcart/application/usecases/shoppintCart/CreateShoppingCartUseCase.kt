package com.shoppingcart.application.usecases.shoppintCart

import com.shoppingcart.boundaries.model.response.ItemResponse
import com.shoppingcart.boundaries.model.response.ShoppingCartResponse
import com.shoppingcart.boundaries.model.response.ShoppingCartResponseBuilder
import com.shoppingcart.domain.ShoppingCart
import com.shoppingcart.domain.request.ItemRequest
import com.shoppingcart.infrastructure.entities.Client
import com.shoppingcart.infrastructure.repositories.ClientRepository
import com.shoppingcart.infrastructure.repositories.ItemRepository
import com.shoppingcart.infrastructure.repositories.ShoppingCartRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*
import com.shoppingcart.infrastructure.entities.Item as ItemEntity
import com.shoppingcart.infrastructure.entities.ShoppingCart as ShoppingCartEntity

@Component
class CreateShoppingCartUseCase(
    val clientRepository: ClientRepository,
    val itemRepository: ItemRepository,
    val shoppingCartRepository: ShoppingCartRepository
) {
    val shoppingCartResponseBuilder = ShoppingCartResponseBuilder()

    @Transactional
    operator fun invoke(shoppingCart: ShoppingCart): ShoppingCartResponse {
        val client = clientRepository.findById(shoppingCart.clientId).orElseThrow{
            NoSuchElementException("Client not found")
        }
        val orderId = UUID.randomUUID()
        val itemsEntity = mutableListOf<ItemEntity>()

        shoppingCart.items.map {
            val item = itemRepository.findById(it.id).orElseThrow{
                NoSuchElementException("Item not found")
            }
            itemsEntity.add(item)
            val shoppingCartEntity = ShoppingCartEntity(
                orderId = orderId,
                client = client,
                item = item,
                quantityItem = it.quantity
            )
            shoppingCartRepository.save(shoppingCartEntity)
        }
        return getShoppingCartResponse(client, orderId, itemsEntity, shoppingCart.items)
    }

    private fun getShoppingCartResponse(
        client: Client,
        orderId: UUID,
        itemsEntity: List<ItemEntity>,
        itemRequest: List<ItemRequest>
    ): ShoppingCartResponse {

        val items = itemsEntity.map { item ->
            val quantity = itemRequest.first{it.id == item.id}.quantity
            ItemResponse(
                id = item.id!!,
                name = item.name,
                value = item.value,
                quantity = quantity,
                amount = (item.value * quantity)
            )
        }

        return shoppingCartResponseBuilder(
            orderId = orderId,
            client = client,
            items = items
        )
    }
}