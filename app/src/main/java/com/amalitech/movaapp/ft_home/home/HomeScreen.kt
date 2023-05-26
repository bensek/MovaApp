package com.amalitech.movaapp.ft_home.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.amalitech.movaapp.R
import com.amalitech.movaapp.core.components.MIconButton
import com.amalitech.movaapp.core.components.LoadingProgressBar
import com.amalitech.movaapp.core.components.MovieListItem
import com.amalitech.movaapp.core.navigation.Screen
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.ft_home.MovieType
import com.amalitech.movaapp.ui.theme.LocalDimensions
import com.amalitech.movaapp.ui.theme.RedMain
import com.amalitech.movaapp.ui.theme.TextBlack

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel(),
    openDetails: (Int) -> Unit
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

            SectionList(title = "Popular Movies", moviesList = state.value.popular,
            seeAll = { navController.navigate(Screen.GridScreen.route + "/${MovieType.Popular.name}") },
                { openDetails(it) } )

            SectionList(
                title = "Upcoming Movies", moviesList = state.value.upcoming,
                seeAll = { navController.navigate(Screen.GridScreen.route + "/${MovieType.Upcoming.name}") },
                openDetails = { openDetails(it) }
            )

            SectionList(
                title = "Top Rated Movies", moviesList = state.value.topRated,
                seeAll = { navController.navigate(Screen.GridScreen.route + "/${MovieType.TopRated.name}") },
                openDetails = { openDetails(it) }
            )

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
                MIconButton(
                    modifier = Modifier.height(32.dp),
                    title = "Play",
                    backgroundColor = RedMain,
                    borderColor = RedMain,
                    icon = R.drawable.play_btn_small,
                    textColor = Color.White,
                    iconColor = Color.White,
                    iconSize = 15.dp,
                    textSize = 10.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                MIconButton(
                    modifier = Modifier.height(32.dp),
                    title = "My List",
                    backgroundColor = Color.Transparent,
                    borderColor = Color.White,
                    iconVector = Icons.Filled.Add,
                    textColor = Color.White,
                    iconColor = Color.White,
                    iconSize = 15.dp,
                    textSize = 10.sp
                )
            }
        }
    }
}

@Composable
fun SectionList(
    title: String,
    moviesList: List<Movie>,
    seeAll: () -> Unit,
    openDetails: (Int) -> Unit
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
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable(onClick = seeAll)
            )
        }

        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(moviesList) { movie ->
                MovieListItem(movie = movie, cardModifier = Modifier
                    .width(140.dp)
                    .height(200.dp), openDetails)
            }
        }
    }

}