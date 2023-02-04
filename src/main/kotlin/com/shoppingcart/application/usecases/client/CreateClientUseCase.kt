package com.shoppingcart.application.usecases.client

import com.shoppingcart.application.commands.CreateClientCommand
import com.shoppingcart.infrastructure.entities.Client
import com.shoppingcart.infrastructure.repositories.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CreateClientUseCase{

    @Autowired
    lateinit var clientRepository: ClientRepository

    operator fun invoke(command: CreateClientCommand): Client{
        val clientEntity = Client(
            name = command.name,
            documentNumber = command.documentNumber,
            email = command.email,
            address = command.address
        )

        return clientRepository.save(clientEntity)
    }
}