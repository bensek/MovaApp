package com.amalitech.movaapp.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amalitech.movaapp.ui.theme.*

@Composable
fun InputField(
    label: String,
    leadingIcon: ImageVector,
    value: String,
    onValueChanged: (String) -> Unit
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        textStyle = TextStyle(fontSize = 14.sp),
        singleLine = true,
        placeholder = {
            Text(text = label, color = GrayStroke)
        },
        shape = RoundedCornerShape(LocalDimensions.current.inputCornerSize),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = SearchBarColor,
            unfocusedBorderColor = SearchBarColor
        ),
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = Color(0xFF9E9E9E)
            )
        }
    )
}