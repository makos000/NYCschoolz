package com.example.nycschoolz.repo

import com.example.nycschoolz.api.ApiInterface
import com.example.nycschoolz.models.SATsModelItem
import com.example.nycschoolz.models.SchoolModelItemModel
import javax.inject.Inject

class RepoRemote @Inject constructor(val apiInterface: ApiInterface): RepoInterface {

    override suspend fun getSchools(): List<SchoolModelItemModel> {
        return apiInterface.getSchools()
    }

    override suspend fun getSAT(string: String): List<SATsModelItem> {
        return apiInterface.getSAT(string = string)
    }
}