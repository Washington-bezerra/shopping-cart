package com.shoppingcart.application.mappers

interface Mapper <T, U>{
    fun map(t: T): U
}