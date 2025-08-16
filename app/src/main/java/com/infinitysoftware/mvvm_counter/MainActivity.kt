package com.infinitysoftware.mvvm_counter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.infinitysoftware.mvvm_getdata.view.CounterView
import com.infinitysoftware.mvvm_getdata.viewmodel.CounterViewModel
import com.infinitysoftware.mvvm_counter.ui.theme.MVVMCounterTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = ViewModelProvider(this)[CounterViewModel::class]

        setContent {
            MVVMCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterView(viewModel)
                }
            }
        }
    }
}