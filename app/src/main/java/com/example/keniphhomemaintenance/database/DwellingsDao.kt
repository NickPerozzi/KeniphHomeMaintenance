package com.example.keniphhomemaintenance.database

import androidx.room.*
import com.example.keniphhomemaintenance.dwellings.Dwelling
import com.example.keniphhomemaintenance.maintenance_screen.MaintenanceItem

@Dao
interface DwellingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDwellingToDb(dwelling: Dwelling): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun prePopulateDwellings(dwellings: List<Dwelling>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMaintenanceItemToDb(maintenanceItem: MaintenanceItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun prePopulateMaintenanceItems(maintenanceItems: List<MaintenanceItem>)

    @Transaction
    @Query("SELECT * FROM dwellings_table WHERE dwellingID = :dwellingID")
    suspend fun getMaintenanceItemsForDwellingFromDb(dwellingID: Int): List<DwellingWithMaintenanceItems>

    @Transaction
    @Query("SELECT * FROM dwellings_table")
    suspend fun getDwellingsListFromDb(): List<Dwelling>

    @Transaction
    @Query("SELECT COUNT(*) FROM maintenance_item_table WHERE maintenanceItemID = :maintenanceItemID")
    fun checkIfDwellingsHavePrePopulatedData(maintenanceItemID: Int): Int

}