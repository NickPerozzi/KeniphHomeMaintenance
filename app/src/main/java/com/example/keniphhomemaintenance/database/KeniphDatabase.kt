package com.example.keniphhomemaintenance.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.keniphhomemaintenance.CompanionObjects.Companion.getDefaultDwellings
import com.example.keniphhomemaintenance.dwellings.Dwelling
import com.example.keniphhomemaintenance.dwellings.DwellingsViewModel.Companion.getDefaultMaintenanceItems
import com.example.keniphhomemaintenance.maintenance_screen.MaintenanceItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities =
[
    MaintenanceItem::class,
    Dwelling::class
], version = 1, exportSchema = true)
abstract class KeniphDatabase: RoomDatabase() {

    abstract fun dwellingsDao(): DwellingsDao

    companion object {
        @Volatile
        private var INSTANCE: KeniphDatabase? = null

        fun getKeniphDatabase(context: Context): KeniphDatabase {
            val tempInstance = INSTANCE

            tempInstance?.let {
                return tempInstance
            } ?: run {

                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        KeniphDatabase::class.java,
                        "keniph_database"
                    ).addCallback(prePopulateDatabaseCallback())
                        .build()

                    INSTANCE = instance
                    return instance
                }

            }
        }

        private fun prePopulateDatabaseCallback(): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    INSTANCE?.dwellingsDao()
                        ?.let { dwellingsDao ->
                            prePopulateDwellings(dwellingsDao)
                            prePopulateMaintenanceItems(dwellingsDao)
                        }
                }
            }
        }

        private fun prePopulateDwellings(dwellingsDao: DwellingsDao) {
            CoroutineScope(Dispatchers.IO).launch {
                dwellingsDao.prePopulateDwellings(
                    getDefaultDwellings()
                )
            }
        }

        private fun prePopulateMaintenanceItems(dwellingsDao: DwellingsDao) {
            val maintenanceItems = getDefaultMaintenanceItems(1) + getDefaultMaintenanceItems(2)
            CoroutineScope(Dispatchers.IO).launch {
                dwellingsDao.prePopulateMaintenanceItems(maintenanceItems)
            }
        }

    }

}