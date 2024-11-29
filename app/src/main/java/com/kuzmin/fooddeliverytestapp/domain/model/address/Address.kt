package com.kuzmin.fooddeliverytestapp.domain.model.address

data class Address(
    val address: String,

    val fullAddress: String,

    val country: String,

    val region: String,

    val regionWithType: String,

    val city: String,

    val cityWithType: String,

    val street: String,

    val streetWithType: String,

    val streetType: String,

    val house: String
)
