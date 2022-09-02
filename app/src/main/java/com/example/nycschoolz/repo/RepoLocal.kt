package com.example.nycschoolz.repo

import com.example.nycschoolz.room.SchoolDAO
import com.example.nycschoolz.room.SchoolEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoLocal @Inject constructor(val schoolDAO: SchoolDAO): LocalRepoInterface{

    override fun insertSchoolsToDB(schoolEntity: SchoolEntity) = schoolDAO.insertSchoolsToDB(schoolEntity)

    override fun readSchoolsFromDB(): Flow<List<SchoolEntity>> = schoolDAO.readSchoolsFromDB()


    //override fun readSchoolsFromDB() = schoolDAO.readSchoolsFromDB()

}