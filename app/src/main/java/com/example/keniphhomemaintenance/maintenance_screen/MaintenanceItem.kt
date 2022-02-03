package com.example.keniphhomemaintenance.maintenance_screen

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "maintenance_item_table")
data class MaintenanceItem(
    @PrimaryKey(autoGenerate = true)
    val maintenanceItemID: Int,
    val icon: Int,
    val title: String,
    val dueDate: String,
    val location: String,
    val dwellingID: Int
)
