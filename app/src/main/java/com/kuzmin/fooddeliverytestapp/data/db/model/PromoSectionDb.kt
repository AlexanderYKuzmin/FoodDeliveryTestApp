package com.kuzmin.fooddeliverytestapp.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "promo_sections")
data class PromoSectionDb(
    @PrimaryKey(true)
    val id: Int,

    val picture: Int,

    val title: String
)
