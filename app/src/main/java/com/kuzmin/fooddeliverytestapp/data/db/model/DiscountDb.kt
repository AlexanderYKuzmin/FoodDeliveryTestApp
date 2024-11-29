package com.kuzmin.fooddeliverytestapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "discounts")
data class DiscountDb(
    @PrimaryKey(true)
    val id: Int,

    val title: String,

    val picture: Int,

    val discount: Int,

    @ColumnInfo(name = "is_new")
    val isNew: Int,

    val weight: Int,

    @ColumnInfo(name = "old_price")
    val oldPrice: Int,

    @ColumnInfo(name = "new_price")
    val newPrice: Int
)
