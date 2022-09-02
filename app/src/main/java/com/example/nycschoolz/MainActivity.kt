package com.example.nycschoolz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.nycschoolz.ui.SchoolList
import com.example.nycschoolz.ui.theme.NYCschoolzTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)

        viewModel.getAllSchools()
        setContent {
            NYCschoolzTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }

    @Composable
    fun MyApp() {

        Text(text = viewModel.schools.toString())
        //SchoolList(schools = viewModel.schools, onClicked = { /*TODO*/ }, viewModel = viewModel)
    }
}



