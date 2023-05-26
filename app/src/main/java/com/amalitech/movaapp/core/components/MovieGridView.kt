package com.amalitech.movaapp.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.ui.theme.LocalDimensions

@Composable
fun MovieGridView(movies: List<Movie>, openDetails: (Int) -> Unit) {
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