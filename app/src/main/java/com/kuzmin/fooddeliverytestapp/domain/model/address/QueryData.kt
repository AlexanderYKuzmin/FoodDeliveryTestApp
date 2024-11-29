package com.kuzmin.fooddeliverytestapp.domain.model.address

data class QueryData(
    val query: String,

    val location: Location? = null
)
