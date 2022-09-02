package com.example.nycschoolz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.nycschoolz.models.SchoolModelItemModel
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
class MainViewModel @Inject constructor(val schoolDAO: SchoolDAO, val repoLocal: RepoLocal, val repoInterface: RepoInterface): ViewModel(){
    var schools: List<SchoolModelItemModel> by mutableStateOf(listOf())

    fun getAllSchools(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val school_list = repoInterface.getSchools()
                if(school_list.isNotEmpty()){
                    nukeData()
                    schools = school_list
                    //insertIntoDatabase(school_list as ArrayList<SchoolModelItemModel>)
                }
            }catch (e: Error){
                Error(e)
            }


        }
    }

    fun insertIntoDatabase(model: ArrayList<SchoolModelItemModel>){
        val schoolEntity = SchoolEntity(model)
        CoroutineScope(Dispatchers.IO).launch {
            schoolDAO.insertSchoolsToDB(schoolEntity)
        }
    }

    fun nukeData(){
        CoroutineScope(Dispatchers.IO).launch{
            schoolDAO.nukeTable()
        }
    }
}