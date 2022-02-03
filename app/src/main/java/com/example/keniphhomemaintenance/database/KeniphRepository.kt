package com.example.keniphhomemaintenance.database

import com.example.keniphhomemaintenance.dwellings.Dwelling
import com.example.keniphhomemaintenance.maintenance_screen.MaintenanceItem

class KeniphRepository(
    private val dwellingsDao: DwellingsDao
) {

    suspend fun addDwellingToDb(dwelling: Dwelling): Long {
        return dwellingsDao.addDwellingToDb(dwelling)
    }

    suspend fun addMaintenanceItemToDb(maintenanceItem: MaintenanceItem) {
        dwellingsDao.addMaintenanceItemToDb(maintenanceItem)
    }

    suspend fun prePopulateMaintenanceItems(maintenanceItems: List<MaintenanceItem>) {
        dwellingsDao.prePopulateMaintenanceItems(maintenanceItems)
    }

    suspend fun getMaintenanceItemsForDwellingFromDb(dwellingID: Int): List<DwellingWithMaintenanceItems> {
        return dwellingsDao.getMaintenanceItemsForDwellingFromDb(dwellingID)
    }

    suspend fun getDwellingsListFromDb(): List<Dwelling> {
        return dwellingsDao.getDwellingsListFromDb()
    }

}
