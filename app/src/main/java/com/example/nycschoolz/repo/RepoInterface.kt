package com.example.nycschoolz.repo

import com.example.nycschoolz.models.SchoolModelItemModel

interface RepoInterface {

    suspend fun getSchools(): List<SchoolModelItemModel>
}