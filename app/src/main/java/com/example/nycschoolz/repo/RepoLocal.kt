package com.example.nycschoolz.repo

import com.example.nycschoolz.room.SchoolDAO
import com.example.nycschoolz.room.SchoolEntity
import javax.inject.Inject

class RepoLocal @Inject constructor(val schoolDAO: SchoolDAO){

    fun insertSchoolsToDB(schoolEntity: SchoolEntity) = schoolDAO.insertSchoolsToDB(schoolEntity)

    fun readSchoolsFromDB() = schoolDAO.readSchoolsFromDB()

}