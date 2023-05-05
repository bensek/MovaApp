package com.amalitech.movaapp.core.components

import androidx.annotation.ColorRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amalitech.movaapp.R
import com.amalitech.movaapp.ui.theme.GrayStroke
import com.amalitech.movaapp.ui.theme.LocalDimensions
import com.amalitech.movaapp.ui.theme.RedMain
import com.amalitech.movaapp.ui.theme.TextBlack

val inputHeightSize = 48.dp
val inputTextSize = 14.sp
val cornerSize = 24.dp


@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    buttonText: String,
    onClick: () -> Unit
) {

    Button(
        modifier = modifier
            .height(inputHeightSize),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = RedMain),
        shape = RoundedCornerShape(cornerSize)
    ) {
        Text(
            text = buttonText,
            fontSize = inputTextSize,
            color = Color.White
        )

    }

}

@Composable
fun SocialButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    buttonIcon: Int,
    onClick: () -> Unit
) {

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(inputHeightSize),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        shape = RoundedCornerShape(LocalDimensions.current.inputCornerSize),
        border = BorderStroke(0.2.dp, GrayStroke)
    ) {
        Image(
            modifier = Modifier.size(20.dp),
            painter = painterResource(buttonIcon) ,
            contentDescription = buttonText
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = buttonText,
            fontSize = inputTextSize,
            color = TextBlack
        )

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewButtons() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)) {
        ButtonPrimary(
            buttonText = "Primary Button"
        ) {

        }
        
        Spacer(modifier = Modifier.height(20.dp))

        SocialButton(
            buttonText = "Social Button",
            buttonIcon = R.drawable.facebook
        ) {

        }
    }

}