package com.example.pr01.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pr01.R
import com.example.pr01.ViewModel.CartViewModel
import com.example.pr01.components.PrimaryButton

@Composable
fun EditCartScreen(
    cartId: String,
    cartViewModel: CartViewModel,
    onSaveSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val cart = cartViewModel.selectedCart
    val isUpdateSuccess = cartViewModel.isUpdateSuccess
    var totalInput by remember { mutableStateOf("") }


    LaunchedEffect(cartId) {
        cartViewModel.getCartById(cartId)
    }


    LaunchedEffect(cart) {
        cart?.total?.let {
            totalInput = it.toString()
        }
    }


    LaunchedEffect(isUpdateSuccess) {
        if (isUpdateSuccess) {
            cartViewModel.resetUpdateState()
            onSaveSuccess()
        }
    }

    val nunitoSans = FontFamily(Font(R.font.nunito_sans))

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
            .padding(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 15.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Products:",
                        fontFamily = nunitoSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        color = Color(0xFF808080)
                    )
                    Text(
                        text = (cart?.totalProducts ?: 0).toString(),
                        fontFamily = nunitoSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFF242424)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))


                Text(
                    text = "Total:",
                    fontFamily = nunitoSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    color = Color(0xFF808080)
                )

                Spacer(modifier = Modifier.height(10.dp))


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF5F5F5), RoundedCornerShape(4.dp))
                        .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(4.dp))
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Column {
                        Text(
                            text = "Total",
                            fontFamily = nunitoSans,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                            color = Color(0xFF808080)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        BasicTextField(
                            value = totalInput,
                            onValueChange = { totalInput = it },
                            singleLine = true,
                            textStyle = TextStyle(
                                fontFamily = nunitoSans,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                color = Color(0xFF242424)
                            ),
                            cursorBrush = SolidColor(Color(0xFF242424)),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            modifier = Modifier.fillMaxWidth(),
                            decorationBox = { innerTextField ->
                                if (totalInput.isEmpty()) {
                                    Text(
                                        text = "Type total price",
                                        fontFamily = nunitoSans,
                                        fontSize = 16.sp,
                                        color = Color(0xFFB3B3B3)
                                    )
                                }
                                innerTextField()
                            }
                        )
                    }
                }
            }
        }


        PrimaryButton(
            text = "Save",
            enabled = totalInput.toDoubleOrNull() != null,
            onClick = {
                val parsedTotal = totalInput.replace(',', '.').toDoubleOrNull()
                if (parsedTotal != null) {
                    cartViewModel.updateCart(cartId, parsedTotal)
                }
            }
        )
    }
}
