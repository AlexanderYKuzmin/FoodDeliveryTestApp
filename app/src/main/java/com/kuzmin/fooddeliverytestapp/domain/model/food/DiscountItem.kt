package com.kuzmin.fooddeliverytestapp.domain.model.food

data class DiscountItem(

    val title: String,

    val picture: Int,

    val discountValue: Int,

    val isNew: Int,

    val weight: Int,

    val oldPrice: Int,

    val newPrice: Int
) {

    private var _amount: Int = 0
    val amount: Int get() = _amount

    fun increaseAmount() {
        _amount++
    }

    fun decreaseAmount() {
        _amount--
    }
}


