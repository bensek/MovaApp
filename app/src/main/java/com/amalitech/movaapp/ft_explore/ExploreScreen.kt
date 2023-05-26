package com.amalitech.movaapp.ft_explore

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.amalitech.movaapp.R
import com.amalitech.movaapp.core.components.LoadingProgressBar
import com.amalitech.movaapp.core.components.MovieGridView
import com.amalitech.movaapp.ui.theme.*

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = hiltViewModel(),
    openDetails: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Column {
        TopLayout(
            text = state.searchQuery,
            onQueryChanged = { viewModel.onSearchQueryChanged(it) }
        )

        if (state.isLoading) {
            LoadingProgressBar()
        } else {
            MovieGridView(
                movies = state.movies,
                openDetails = openDetails
            )
        }
    }

}

@Composable
fun TopLayout(
    text: String,
    onQueryChanged: (String) -> Unit
) {
    val padding = LocalDimensions.current.padding

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            SearchBar(
                text = text,
                onValueChanged = onQueryChanged
            )
            Spacer(modifier = Modifier.width(8.dp))
            FilterButton {

            }
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    text: String,
    onValueChanged: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = text,
        onValueChange = onValueChanged,
        interactionSource = interactionSource,
        placeholder = {
            Text(text = "Search")
        },
        shape = RoundedCornerShape(6.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = if (isFocused) RedMain else EditTextBackground
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = if (isFocused) LightRed else SearchBarColor,
            cursorColor = RedMain,
            unfocusedBorderColor = Color.Transparent,
            //focusedBorderColor = if (isFocused) RedMain else Color.Transparent
        ),
        modifier = Modifier
            .height(50.dp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onValueChanged
                keyboardController?.hide()
            }
        )
    )

}

@Composable
fun FilterButton(
    openFilter: () -> Unit
) {

    IconButton(
        onClick = openFilter,
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(LightRed)
            .size(50.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter),
            contentDescription = "Filter",
            tint = RedMain,
            modifier = Modifier.size(24.dp)
        )

    }

}