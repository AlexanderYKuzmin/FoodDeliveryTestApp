package com.kuzmin.fooddeliverytestapp.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catalog")
data class CatalogDb(
    @PrimaryKey(true)
    val id: Int,

    val title: String,

    val url: String
)