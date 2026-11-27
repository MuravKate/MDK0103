package com.example.pr01.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pr01.ViewModel.CartViewModel
import com.example.pr01.components.CartCard

@Composable
fun CartsScreen(
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier
) {
    val carts by cartViewModel.cartsList.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        cartViewModel.getCarts()
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA)),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(carts) { cart ->
            CartCard(cart = cart)
        }
    }
}
