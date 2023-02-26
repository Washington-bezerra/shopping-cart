package com.shoppingcart.boundaries.controllers
import com.shoppingcart.application.usecases.shoppintCart.CreateShoppingCartUseCase
import com.shoppingcart.boundaries.model.response.ShoppingCartResponse
import com.shoppingcart.domain.ShoppingCart
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ShoppingCartController(
    private val createShoppingCartUseCase: CreateShoppingCartUseCase,
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/shoppingCart")
    fun createShoppingCart(@RequestBody shoppingCart: ShoppingCart): ResponseEntity<ShoppingCartResponse> {
        log.info("Receiving shoppingCart request")

        return try {
            ResponseEntity(createShoppingCartUseCase(shoppingCart), HttpStatus.CREATED)
        }catch (e: Exception) {
            if (e.message == "Client not found" || e.message == "Item not found") {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }else{
                ResponseEntity(HttpStatus.BAD_REQUEST)
            }

        }
    }

}
