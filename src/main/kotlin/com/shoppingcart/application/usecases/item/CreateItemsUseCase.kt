package com.shoppingcart.application.usecases.item

import com.shoppingcart.infrastructure.entities.Item
import com.shoppingcart.infrastructure.repositories.ItemRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.stereotype.Component
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder


@Component
class CreateItemsUseCase(
    @Value("\${kafka.topics.product}") val topic: String,
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var itemRepository: ItemRepository

    operator fun invoke(items: List<Item>): MutableList<Item> {
        val itemsPersisted = itemRepository.saveAll(items)
        log.info("Items saved in DB")

        items.forEach {
            item -> sendMessageToTopic(item)
        }

        return itemsPersisted
    }

    private fun sendMessageToTopic(item: Item) {
        val message: Message<Item> = MessageBuilder
            .withPayload(item)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .setHeader("X-Custom-Header", "Custom header here")
            .build()
        kafkaTemplate.send(message)
        log.info("Item {} sent to with success", item)
    }
}