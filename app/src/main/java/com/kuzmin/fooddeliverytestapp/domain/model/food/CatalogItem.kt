package com.kuzmin.fooddeliverytestapp.domain.model.food

import com.kuzmin.fooddeliverytestapp.R

data class CatalogItem(

    val title: String,

    val pictureId: Int,

    val colorId: Int = R.color.white
)
