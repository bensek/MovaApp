package com.amalitech.movaapp.ft_home.home

import android.icu.text.CaseMap.Title
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.amalitech.movaapp.R
import com.amalitech.movaapp.core.components.LoadingProgressBar
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.ui.theme.LocalDimensions
import com.amalitech.movaapp.ui.theme.RedMain
import com.amalitech.movaapp.ui.theme.TextBlack

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val state = viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    if (state.value.isLoading) {
        LoadingProgressBar()
    } else {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            FeaturedMovie(movie = state.value.featured!!)

            SectionList(title = "Popular Movies", moviesList = state.value.popular)

            SectionList(title = "Top Rated Movies", moviesList = state.value.topRated)

            SectionList(title = "Upcoming Movies", moviesList = state.value.upcoming)
        }
    }
}

@Composable
fun FeaturedMovie(movie: Movie) {
    Box(
        modifier = Modifier
            .height(280.dp)
    ) {
        AsyncImage(
            model = movie.imageUrl,
            contentDescription = movie.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalDimensions.current.padding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.White,

                    )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalDimensions.current.padding)
                .align(Alignment.BottomStart),
        ) {
            Text(
                text = movie.title,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = movie.genre,
                fontSize = 10.sp,
                color = Color.White
            )

            Row(modifier = Modifier.padding(top = 4.dp)) {
                FeaturedButton(title = "Play", backgroundColor = RedMain, borderColor = RedMain, icon = com.amalitech.movaapp.R.drawable.play_btn_small)
                Spacer(modifier = Modifier.width(8.dp))
                FeaturedButton(title = "My List", backgroundColor = Color.Transparent, borderColor = Color.White, iconVector = Icons.Filled.Add)
            }

        }

    }
}

@Composable
fun SectionList(
    title: String,
    moviesList: List<Movie>
) {

    Column(
        modifier = Modifier.padding(top = LocalDimensions.current.padding)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalDimensions.current.padding),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextBlack
            )

            Text(
                text = "See all",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = RedMain,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(moviesList) { movie ->
                SectionListItem(movie = movie)
            }
        }
    }

}

@Composable
fun SectionListItem(movie: Movie) {

    Box {
        Card(
            modifier = Modifier
                .width(140.dp)
                .height(200.dp),
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