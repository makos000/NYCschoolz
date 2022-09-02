package com.example.nycschoolz.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nycschoolz.models.SchoolModelItemModel

@Entity(tableName = "schools")
class SchoolEntity(val schoolEntity: ArrayList<SchoolModelItemModel>) {
    @PrimaryKey(autoGenerate = true)
    var index: Int = 0
}