package com.shoppingcart.event
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.ObjectMapper
import com.shoppingcart.event.avro.item.Item
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer
import org.slf4j.LoggerFactory


class ItemSerializer : Serializer<Item> {
    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun serialize(topic: String?, data: Item?): ByteArray? {
        log.info("Serializing...")
        objectMapper.addMixIn(Item::class.java, IgnoreSchemaProperty::class.java)
        return objectMapper.writeValueAsBytes(
            data ?: throw SerializationException("Error when serializing Item to ByteArray[]")
        )
    }

    override fun close() {}
}

abstract class IgnoreSchemaProperty {
    @get:JsonIgnore
    abstract val schema: Unit
}