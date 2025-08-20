package com.infinitysoftware.mvvm_getdata.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infinitysoftware.mvvm_counter.R
import com.infinitysoftware.mvvm_counter.model.CounterModel
import com.infinitysoftware.mvvm_getdata.ui.theme.*
import com.infinitysoftware.mvvm_getdata.viewmodel.CounterViewModel
import kotlinx.coroutines.launch

@Composable
fun CounterView(viewModel: CounterViewModel) {
    val counter by viewModel.counter.observeAsState(CounterModel())
    val count = counter.count
    val countStep = counter.countStep

    var counterFontColor = NeutralCounterFontColor
    var counterStepFontColor = NeutralCounterStepFontColor

    var isCountStepEditable by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    if (count < 0) {
        counterFontColor = DecrementButtonBackgroundColor
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                    isCountStepEditable = false
                }
            },
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            Text(text = "$count", fontSize = 80.sp, color = counterFontColor)

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

            Spacer(modifier = Modifier.size(16.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Current Step Value", fontSize = 30.sp, color = NeutralCounterStepFontColor)

                Spacer(modifier = Modifier.size(8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .pointerInput(isCountStepEditable) {
                            detectTapGestures {
                                if (isCountStepEditable) {
                                    isCountStepEditable = !isCountStepEditable
                                }
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (isCountStepEditable) {
                        TextField(
                            value = countStep.toString(),
                            onValueChange = { newValue ->
                                if (newValue.isEmpty()) viewModel.setCountStep(0)
                                else if (newValue.length <= 5 && newValue.all { it.isDigit() }) {
                                    viewModel.setCountStep(newValue.toInt())
                                }
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                focusedIndicatorColor = NeutralCounterStepFontColor,
                                cursorColor = NeutralCounterStepFontColor,
                            ),
                            textStyle = LocalTextStyle.current.copy(
                                color = NeutralCounterStepFontColor,
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .width(150.dp)
                                .focusRequester(focusRequester)
                        )

                        LaunchedEffect(Unit) {
                            scope.launch { focusRequester.requestFocus() }
                        }

                    } else {
                        Text(
                            text = "$countStep",
                            fontSize = 30.sp,
                            color = counterStepFontColor,
                            modifier = Modifier.clickable {
                                isCountStepEditable = !isCountStepEditable
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            Row {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Button(onClick = { viewModel.counterStepDecrement() }, colors = ButtonDefaults.buttonColors(containerColor = DecrementCounterStepFontColor)) {
                        Text(text = "Step: -")
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Button(onClick = { viewModel.counterStepReset() }, colors = ButtonDefaults.buttonColors(containerColor = NeutralCounterStepFontColor)) {
                        Text(text = "Step: 1")
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Button(onClick = { viewModel.counterStepIncrement() }, colors = ButtonDefaults.buttonColors(containerColor = IncrementCounterStepFontColor)) {
                        Text(text = "Step: +", fontWeight = Bold)
                    }
                }
            }
        }
    }
}