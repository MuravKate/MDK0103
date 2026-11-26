package com.example.pr01.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pr01.R

@Composable
fun LoginHeaderLogo(
    modifier: Modifier = Modifier,
    @DrawableRes logoResId: Int = R.drawable.group
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = Color(0xFFBDBDBD)
        )

        Surface(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .size(64.dp),
            shape = CircleShape,
            border = BorderStroke(1.dp, Color(0xFF303030)),
            color = Color.White
        ) {
            Image(
                painter = painterResource(id = logoResId),
                contentDescription = "Logo",
                modifier = Modifier.padding(10.dp)
            )
        }

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = Color(0xFFBDBDBD)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginHeaderLogoPreview() {
    LoginHeaderLogo(logoResId = R.drawable.ic_launcher_foreground)
}
