package com.example.nycschoolz.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschoolz.models.SATsModelItem
import com.example.nycschoolz.models.SchoolModelItemModel
import com.example.nycschoolz.repo.LocalRepoInterface
import com.example.nycschoolz.repo.RepoInterface
import com.example.nycschoolz.repo.RepoLocal
import com.example.nycschoolz.room.SchoolDAO
import com.example.nycschoolz.room.SchoolEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val schoolDAO: SchoolDAO, val repoLocal: LocalRepoInterface, val repoInterface: RepoInterface): ViewModel(){
    var schools: List<SchoolModelItemModel> by mutableStateOf(listOf())
    var sat: List<SATsModelItem> by mutableStateOf(listOf())
    var data_DB = listOf<SchoolEntity>()
    var schoolList = mutableStateListOf<SchoolModelItemModel>()
    var schoolForDS = SchoolModelItemModel()
    var loaded = false

    fun getAllSchools(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val school_list = repoInterface.getSchools()
                schools = school_list
                if(school_list.isNotEmpty()){
                    nukeData()
                    //
                    insertIntoDatabase(school_list as ArrayList<SchoolModelItemModel>)
                }
            }catch (e: Error){
                Error(e)
            }
        }
    }

    fun getSAT(string: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val sat_list = repoInterface.getSAT(string)
                sat = sat_list
            }catch (e: Error){
                Error(e)
            }
        }
    }

    fun getDataFromDB() {
        CoroutineScope(Dispatchers.IO).launch(){
            viewModelScope.launch {
                repoLocal.readSchoolsFromDB().collect { response ->
                    data_DB = response
                    putDataInList()
                }
            }
        }
    }

    fun putDataInList(){
        if (data_DB.isNotEmpty()){
            var school = data_DB[0].schoolEntity

            for (item in school){
                schoolList.add(
                    SchoolModelItemModel(
                        item.academicopportunities1,item.boro,item.buildingCode,item.dbn,
                        item.bus,item.campusName,item.city,item.location,item.neighborhood,
                        item.overviewParagraph,item.phoneNumber,item.schoolEmail,item.schoolName,
                        item.subway,item.totalStudents,item.website,item.zip
                    )
                )
            }
        }
    }

    fun insertIntoDatabase(model: ArrayList<SchoolModelItemModel>){
        val schoolEntity = SchoolEntity(model)
        schoolDAO.insertSchoolsToDB(schoolEntity)
        //repoLocal.insertSchoolsToDB(schoolEntity)

    }

    fun nukeData(){
        CoroutineScope(Dispatchers.IO).launch{
            schoolDAO.nukeTable()
        }
    }
}