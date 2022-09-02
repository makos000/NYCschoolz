package com.example.nycschoolz.repo

import com.example.nycschoolz.models.SATsModelItem
import com.example.nycschoolz.models.SchoolModelItemModel
import com.example.nycschoolz.room.SchoolEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepoInterface {

    fun insertSchoolsToDB(schoolEntity: SchoolEntity)

    fun readSchoolsFromDB(): Flow<List<SchoolEntity>>
}