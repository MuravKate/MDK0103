package com.example.pr01.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.pr01.R

@Composable
fun LoginTitle(
    modifier: Modifier = Modifier,
    greeting: String = "Hello !",
    subtitle: String = "WELCOME BACK"
) {
    val merriweatherFamily = FontFamily(
        Font(R.font.merriweather_regular, FontWeight.Normal),
        Font(R.font.merriweather_bold, FontWeight.Bold)
    )

    Column(modifier = modifier) {
        Text(
            text = greeting,
            fontFamily = merriweatherFamily,
            fontWeight = FontWeight(400),
            fontSize = 30.sp,
            lineHeight = 45.sp,
            color = Color(0xFF909090)
        )
        Text(
            text = subtitle,
            fontFamily = merriweatherFamily,
            fontWeight = FontWeight(700),
            fontSize = 24.sp,
            lineHeight = 45.sp,
            letterSpacing = 1.2.sp,
            color = Color(0xFF303030)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginTitlePreview() {
    LoginTitle()
}
