package com.shoppingcart.boundaries.controllers
import com.shoppingcart.application.commands.CreateClientCommand
import com.shoppingcart.application.usecases.client.CreateClientUseCase
import com.shoppingcart.domain.Client
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.shoppingcart.infrastructure.entities.Client as ClientEntity

@RestController
class ClientController(
    private val createClientUseCase: CreateClientUseCase
) {

    @PostMapping("/client")
    fun createClient(@RequestBody client: Client) : ClientEntity
    {
        val command = CreateClientCommand(
            name = client.name,
            documentNumber = client.documentNumber,
            email = client.email,
            address = client.address
        )
        return createClientUseCase(command)
    }
}