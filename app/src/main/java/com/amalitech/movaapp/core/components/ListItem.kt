package com.amalitech.movaapp.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.amalitech.movaapp.domain.model.Movie

@Composable
fun MovieListItem(
    movie: Movie,
    cardModifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {

    Box {
        Card(
            modifier = cardModifier
                .clickable {
                    onClick(movie.id)
                },
            shape = RoundedCornerShape(8.dp)
        ) {
            AsyncImage(
                model = movie.imageUrl,
                contentDescription = movie.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}