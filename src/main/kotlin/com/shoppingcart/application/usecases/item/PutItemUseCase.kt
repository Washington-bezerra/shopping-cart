package com.shoppingcart.application.usecases.item

import com.shoppingcart.domain.ItemStatus
import com.shoppingcart.infrastructure.repositories.ItemRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class PutItemUseCase{
    private val log = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var itemRepository: ItemRepository

    operator fun invoke(itemId: Int, status: ItemStatus): ResponseEntity<*> {
        val item = itemRepository.updateStatusById(status.toString(), itemId)

        return ResponseEntity(item, HttpStatus.OK)

    }

}