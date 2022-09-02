package com.example.nycschoolz.repo

import com.example.nycschoolz.models.SATsModelItem
import com.example.nycschoolz.models.SchoolModelItemModel

interface RepoInterface {

    suspend fun getSchools(): List<SchoolModelItemModel>

    suspend fun getSATs(): List<SATsModelItem>
}