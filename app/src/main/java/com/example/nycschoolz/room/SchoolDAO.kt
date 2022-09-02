package com.example.nycschoolz.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchoolsToDB(schoolEntity: SchoolEntity)

    @Query("SELECT * FROM schools order by `index` DESC")
    fun readSchoolsFromDB(): Flow<List<SchoolEntity>>

    @Query("DELETE FROM schools")
    fun nukeTable()
}