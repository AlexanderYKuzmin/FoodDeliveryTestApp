package com.kuzmin.fooddeliverytestapp.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "promo_actions")
data class PromoActionDb(
    @PrimaryKey(true)
    val id: Int,

    val picture: Int
)