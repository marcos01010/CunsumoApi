package com.example.cunsumoapi.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cunsumoapi.service.model.PhotoModel

@Database(entities = [PhotoModel::class], version = 1)
abstract class PhotoDb: RoomDatabase() {
    abstract fun PhotoDAO(): PhotoDAO

    companion object{
        private lateinit var INSTANCE: PhotoDb

        fun getDatabase(context: Context): PhotoDb{
            if(!Companion::INSTANCE.isInitialized){
                synchronized(PhotoDb::class){
                    INSTANCE = Room.databaseBuilder(context, PhotoDb::class.java, "photoDb")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}