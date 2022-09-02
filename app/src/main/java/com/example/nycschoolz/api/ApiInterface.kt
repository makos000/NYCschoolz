package com.example.nycschoolz.api

import com.example.nycschoolz.models.SATsModelItem
import com.example.nycschoolz.models.SchoolModelItemModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools(): List<SchoolModelItemModel>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getSAT(@Query("dbn") string: String): List<SATsModelItem>
}