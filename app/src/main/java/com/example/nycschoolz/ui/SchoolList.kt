package com.example.nycschoolz.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Card(onClick = {onClicked.invoke()
                           viewModel.schoolForDS = item},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = item.schoolName!!, fontWeight = FontWeight.Bold, fontSize = 25.sp)
                    Text(text = item.website!!, fontStyle = FontStyle.Italic, fontSize = 17.sp)
                    Text(text = item.location!!, fontSize = 17.sp)
                    //Text(text = item.dbn, fontSize = 17.sp)
                }
            }
        }
    }
}