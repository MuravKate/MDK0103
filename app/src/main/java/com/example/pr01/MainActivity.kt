package com.example.pr01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import com.example.pr01.ViewModel.ProductViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(Unit) {
                viewModel.loadAndModifyProduct()
            }
        }
    }
}