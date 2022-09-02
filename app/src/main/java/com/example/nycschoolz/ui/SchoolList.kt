package com.example.nycschoolz.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nycschoolz.MainViewModel
import com.example.nycschoolz.models.SchoolModelItemModel
import com.example.nycschoolz.util.CheckInternet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SchoolList(schools: List<SchoolModelItemModel>, onClicked: () -> Unit, viewModel: MainViewModel) {
    //Text(text = viewModel.schools.toString())
    LazyColumn(){
        itemsIndexed(items = schools){
            index, item ->
            Card(onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp,
            ) {
                Column() {
                    Text(text = item.schoolName)
                    Text(text = item.website)
                    Text(text = item.boro)
                }
            }
        }
    }
}