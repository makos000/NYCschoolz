package com.example.nycschoolz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.nycschoolz.ui.DetailScreen
import com.example.nycschoolz.ui.SchoolList
import com.example.nycschoolz.ui.theme.NYCschoolzTheme
import com.example.nycschoolz.util.CheckInternet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    var internet_bool = false
    val internet = CheckInternet()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch() {
            internet_bool = internet.isInternetAvailable("8.8.8.8", 53, 1000)
        }

        setContent {
            NYCschoolzTheme {
                Surface(modifier = Modifier.background(Color.Black)) {
                    MyApp()
                }

            }
        }
    }

    @SuppressLint("UnrememberedMutableState")
    @OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class)
    @Composable
    fun MyApp() {
        var showDetail by rememberSaveable { mutableStateOf(false) }

        if (showDetail) {
            DetailScreen(onClicked = { showDetail = false },viewModel)
        } else {
            if (internet_bool) {
                viewModel.getAllSchools()
                SchoolList(schools = viewModel.schools, onClicked = { showDetail = true }, viewModel = viewModel,internet_bool)
            }
            else{
                viewModel.getDataFromDB()
                if (viewModel.schoolList.isNotEmpty()){
                    SchoolList(schools = viewModel.schoolList, onClicked = {showDetail = true},viewModel,internet_bool)
                }
                else{
                    Text(text = "You are offline")
                }
            }

        }


    }
}



