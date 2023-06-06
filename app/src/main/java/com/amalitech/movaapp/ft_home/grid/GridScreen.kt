package com.amalitech.movaapp.ft_home.grid

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.amalitech.movaapp.core.components.LoadingProgressBar
import com.amalitech.movaapp.core.components.MovieListItem
import com.amalitech.movaapp.core.components.ToolBar
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.ui.theme.LocalDimensions
import com.amalitech.movaapp.ui.theme.TextBlack

@Composable
fun GridScreen(
    navigateUp: () -> Unit,
    viewModel: GridViewModel = hiltViewModel(),
    movieType: String?,
    onItemClick: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    Scaffold(
        topBar = { ToolBar(navigateUp = navigateUp, title = "$movieType Movies", isTransparent = false) }
    ) {
        println(it)
        if (state.isLoading) {
            LoadingProgressBar()
        } else if (state.hasError) {
            Toast.makeText(context, state.errorMessage, Toast.LENGTH_LONG).show()
        } else {
            GridItems(
                movies = state.movies,
                openDetails = onItemClick
            )
        }
    }
}

@Composable
fun GridItems(movies: List<Movie>, openDetails: (Int) -> Unit) {
    val padding = LocalDimensions.current.padding
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(padding),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies) { movie ->
            MovieListItem(
                movie = movie,
                cardModifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                onClick = openDetails
            )
        }

    }

}