package com.example.keniphhomemaintenance.database

import androidx.room.Embedded
import androidx.room.Relation
import com.example.keniphhomemaintenance.dwellings.Dwelling
import com.example.keniphhomemaintenance.maintenance_screen.MaintenanceItem

data class DwellingWithMaintenanceItems (
    @Embedded val dwelling: Dwelling,
    @Relation(
        parentColumn = "dwellingID",
        entityColumn = "dwellingID"
    )
    val maintenanceItems: List<MaintenanceItem>
)