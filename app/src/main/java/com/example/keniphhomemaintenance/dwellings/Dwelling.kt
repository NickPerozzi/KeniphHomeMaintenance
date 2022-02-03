package com.example.keniphhomemaintenance.dwellings

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dwellings_table")
class Dwelling(
    @PrimaryKey(autoGenerate = true)
    val dwellingID: Int,
    val name: String,
    val address: String
)
