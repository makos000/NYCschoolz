package com.example.nycschoolz.models

/*
 academic opportunities1
 zip
 city
 boro
 bus
 subway
 email
 schoolname
 website
 requirement11
  */

import com.google.gson.annotations.SerializedName

data class SchoolModelItemModel(
    @SerializedName("academicopportunities1")
    val academicopportunities1: String,
    @SerializedName("boro")
    val boro: String,
    @SerializedName("building_code")
    val buildingCode: String,
    @SerializedName("bus")
    val bus: String,
    @SerializedName("campus_name")
    val campusName: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("overview_paragraph")
    val overviewParagraph: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("school_email")
    val schoolEmail: String,
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("subway")
    val subway: String,
    @SerializedName("total_students")
    val totalStudents: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("zip")
    val zip: String
)