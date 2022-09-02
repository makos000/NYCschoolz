package com.example.nycschoolz.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nycschoolz.MainViewModel
import com.example.nycschoolz.util.CheckInternet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(onClicked: () -> Unit, viewModel: MainViewModel) {
    Surface {
        Row() {
            IconButton(onClick = onClicked) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back button",
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val scroll_desc = rememberScrollState(0)
            val scroll_bus = rememberScrollState(0)
            val scroll_subway = rememberScrollState(0)
            Text(text = viewModel.schoolForDS.schoolName!!, fontSize = 35.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center, modifier = Modifier.padding(4.dp))
            Text(text = viewModel.schoolForDS.location!!, fontSize = 22.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center, modifier = Modifier.padding(4.dp))
            Text(text = viewModel.schoolForDS.schoolEmail!!, fontSize = 20.sp, fontWeight = FontWeight.Medium, fontStyle = FontStyle.Italic, textAlign = TextAlign.Center, modifier = Modifier.padding(4.dp))
            Text(text = viewModel.schoolForDS.website!!, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, textAlign = TextAlign.Center, modifier = Modifier.padding(4.dp))
            Card(modifier = Modifier
                .height(100.dp)
                .padding(6.dp), elevation = 2.dp, shape = RoundedCornerShape(10.dp)) {
                Text(text = viewModel.schoolForDS.overviewParagraph!!, fontSize = 17.sp, fontWeight = FontWeight.Light, textAlign = TextAlign.Left,
                    modifier = Modifier
                        .verticalScroll(scroll_desc))
            }
            Text(text = "Bus connections: ", fontSize = 22.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
            Card(modifier = Modifier
                .height(50.dp)
                .padding(6.dp)
                .fillMaxWidth(), elevation = 2.dp, shape = RoundedCornerShape(10.dp)) {
                Text(text = viewModel.schoolForDS.bus!!, fontWeight = FontWeight.Light, textAlign = TextAlign.Left,
                    modifier = Modifier
                        .verticalScroll(scroll_bus))
            }
            Text(text = "Subway connections: ", fontSize = 22.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
            Card(modifier = Modifier
                .height(50.dp)
                .padding(6.dp)
                .fillMaxWidth(), elevation = 2.dp, shape = RoundedCornerShape(10.dp)) {
                Text(text = viewModel.schoolForDS.subway!!, fontWeight = FontWeight.Light, textAlign = TextAlign.Left,
                    modifier = Modifier
                        .verticalScroll(scroll_subway))
            }
            Text(text = "DBN: " + viewModel.schoolForDS.dbn!!, fontSize = 22.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
            if (viewModel.sat.isNotEmpty()){
                Row(Modifier.fillMaxWidth()) {
                    Text(text = "Sat test takers#: ")
                    Text(text = viewModel.sat[0].numOfSatTestTakers, fontWeight = FontWeight.Light)
                }
                Row(Modifier.fillMaxWidth()) {
                    Text(text = "Math average Score: ")
                    Text(text = viewModel.sat[0].satMathAvgScore, fontWeight = FontWeight.Light)
                }
                Row(Modifier.fillMaxWidth()) {
                    Text(text = "Writing average Score: ")
                    Text(text = viewModel.sat[0].satWritingAvgScore, fontWeight = FontWeight.Light)
                }
                Row(Modifier.fillMaxWidth()) {
                    Text(text = "Critical Reading average Score: ")
                    Text(text = viewModel.sat[0].satCriticalReadingAvgScore, fontWeight = FontWeight.Light)
                }




            }

        }
    }
}