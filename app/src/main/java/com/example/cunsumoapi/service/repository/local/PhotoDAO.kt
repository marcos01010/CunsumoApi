package com.example.cunsumoapi.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cunsumoapi.service.model.PhotoModel

@Dao
interface PhotoDAO {

    @Insert
    fun save(list: List<PhotoModel>)

    @Query("DELETE FROM photos")
    fun clear()

    @Query("SELECT * FROM photos")
    fun list(): List<PhotoModel>

    @Query("SELECT COUNT(*) FROM photos")
    fun countPhotos(): Int

    @Query("SELECT * FROM photos LIMIT :qunatidade OFFSET :start")
    fun list(qunatidade: Int, start: Int): List<PhotoModel>
}