package com.example.nycschoolz.api

import com.example.nycschoolz.models.SATsModelItem
import com.example.nycschoolz.models.SchoolModelItemModel
import retrofit2.http.GET

interface ApiInterface {

    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools(): List<SchoolModelItemModel>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getSATs(): List<SATsModelItem>
}