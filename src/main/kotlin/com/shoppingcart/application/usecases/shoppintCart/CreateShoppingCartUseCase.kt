package com.shoppingcart.application.usecases.shoppintCart

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
    ): ShoppingCartResponse{

        val items = itemsEntity.map {
            item -> ItemResponse(
                id = item.id!!,
                name = item.name,
                value = item.value,
                quantity = itemRequest.first{it.id == item.id}.quantity
            )
        }

        return ShoppingCartResponse(
            orderId = orderId,
            client = client,
            items = items
        )
    }
}

data class ShoppingCartResponse(
    val orderId: UUID,
    val client: Client,
    val items: List<ItemResponse>
)

data class ItemResponse(
    val id: Int,
    val name: String,
    val value: Double,
    val quantity: Int
)
