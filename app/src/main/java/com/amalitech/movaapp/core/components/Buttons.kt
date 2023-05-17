package com.amalitech.movaapp.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
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

@Composable
fun MIconButton(
    modifier: Modifier = Modifier,
    title: String,
    backgroundColor: Color,
    borderColor: Color,
    textColor: Color,
    iconColor: Color,
    icon: Int? = null,
    iconVector: ImageVector? = null,
    iconSize: Dp,
    textSize: TextUnit
) {
    Button(onClick = { /*TODO*/ },
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        border = BorderStroke(1.dp, borderColor)
    ) {
        if (icon != null) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = iconColor
            )
        }
        if (iconVector != null) {
            Icon(
                imageVector = iconVector,
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = iconColor
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title, color = textColor, fontSize = textSize)

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