package com.example.nycschoolz.repo

import com.example.nycschoolz.models.SATsModelItem
import com.example.nycschoolz.models.SchoolModelItemModel
import com.example.nycschoolz.room.SchoolDAO
import com.example.nycschoolz.room.SchoolEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeRepo @Inject constructor(val schoolDAO: SchoolDAO): LocalRepoInterface {

    var list : MutableList<SchoolModelItemModel> = mutableListOf()

    override fun insertSchoolsToDB(schoolEntity: SchoolEntity) {
        list.add(SchoolModelItemModel("NYC"))
    }

    override fun readSchoolsFromDB(): Flow<List<SchoolEntity>> {
        return flow { list }
    }


}