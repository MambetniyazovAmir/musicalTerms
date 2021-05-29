package com.musicalTerms.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "term")
data class Term(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "word")
    val word : String,

    @ColumnInfo(name = "translation")
    val translation : String
)