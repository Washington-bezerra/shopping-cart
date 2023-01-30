package com.shoppingcart.application.usecases.item

import com.shoppingcart.infrastructure.entities.Item
import com.shoppingcart.infrastructure.repositories.ItemRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import com.shoppingcart.event.avro.item.Item as ItemAvro


@Component
class CreateItemsUseCase(
    @Value("\${kafka.topics.product}") val topic: String,
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, ItemAvro>
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var itemRepository: ItemRepository

    operator fun invoke(items: List<Item>): MutableList<Item> {
        val itemsPersisted = itemRepository.saveAll(items)
        log.info("Items saved in DB $itemsPersisted")

        items.forEach {
            item -> sendMessageToTopic(createItemAvro(item))
        }

        return itemsPersisted
    }

    private fun sendMessageToTopic(itemAvro: ItemAvro) {
        log.info("Trying send message for item ${itemAvro.itemId}")
        val message: Message<ItemAvro> = MessageBuilder
            .withPayload(itemAvro)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .setHeader("X-Custom-Header", "Custom header here")
            .build()
        kafkaTemplate.send(message)
        log.info("Item {} sent to with success", itemAvro.itemId)
    }

    private fun createItemAvro(item: Item) : ItemAvro {
        log.info("Creating item avro ${item.id}")
        return ItemAvro.newBuilder()
            .setItemId(item.id!!)
            .setName(item.name)
            .setValue(item.value)
            .setStatus(item.status.toString())
            .build()
    }
}