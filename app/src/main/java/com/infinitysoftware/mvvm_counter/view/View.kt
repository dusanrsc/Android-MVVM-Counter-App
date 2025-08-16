package com.infinitysoftware.mvvm_getdata.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.infinitysoftware.mvvm_getdata.ui.theme.*
import com.infinitysoftware.mvvm_getdata.viewmodel.CounterViewModel

@Composable
fun CounterView(viewModel: CounterViewModel) {
    val count by viewModel.count.observeAsState(0)
    var counterFontColor = NeutralCounterFontColor

    if (count > 0) {
        counterFontColor = IncrementButtonBackgroundColor
    } else if (count < 0) {
        counterFontColor = DecrementButtonBackgroundColor
    }

    println(count)

    Box(modifier = Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {


            Text(text = "$count", fontSize = 100.sp, color = counterFontColor)

            Row {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Button(onClick = { viewModel.counterDecrement() }, colors = ButtonDefaults.buttonColors(containerColor = DecrementButtonBackgroundColor)) {
                        Text(text = "Decrement")
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Button(onClick = { viewModel.counterReset() }, colors = ButtonDefaults.buttonColors(containerColor = NeutralCounterFontColor)) {
                        Text(text = "    Reset    ")
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Button(onClick = { viewModel.counterIncrement() }, colors = ButtonDefaults.buttonColors(containerColor = IncrementButtonBackgroundColor)) {
                        Text(text = "Increment")
                    }
                }
            }
        }
    }
}