package com.amalitech.movaapp.ft_home.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FeaturedButton(
    title: String,
    backgroundColor: Color,
    borderColor: Color,
    icon: Int? = null,
    iconVector: ImageVector? = null,
) {
    Button(onClick = { /*TODO*/ },
        modifier = Modifier
            .height(32.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        border = BorderStroke(1.dp, borderColor)
    ) {
        if (icon != null) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(15.dp),
                tint = Color.White
            )
        }
        if (iconVector != null) {
            Icon(
                imageVector = iconVector,
                contentDescription = null,
                modifier = Modifier.size(15.dp),
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title, color = Color.White, fontSize = 10.sp)

    }
}