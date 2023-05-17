package com.amalitech.movaapp.ft_home.detail

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.amalitech.movaapp.R
import com.amalitech.movaapp.core.components.LoadingProgressBar
import com.amalitech.movaapp.core.components.MIconButton
import com.amalitech.movaapp.core.components.MovieListItem
import com.amalitech.movaapp.core.components.ToolBar
import com.amalitech.movaapp.domain.model.Credit
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.domain.model.Video
import com.amalitech.movaapp.ui.theme.*

@Composable
fun DetailScreen(
    navController: NavHostController,
    viewModel: DetailViewModel
) {
    val padding = LocalDimensions.current.padding
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    if (state.isLoading) {
        LoadingProgressBar()
    } else if (state.isFailure) {
        Toast.makeText(context, state.errorMsg, Toast.LENGTH_SHORT).show()
    } else {
        DetailLayout(padding = padding, state = state, navController)
    }
}

@Composable
fun DetailLayout(
    padding: Dp,
    state: DetailUiState,
    navController: NavHostController
) {
    val movie = state.movie!!
    var scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .verticalScroll(scrollState),
    ) {
        // Header Image
        Box(
            modifier = Modifier
                .height(220.dp)
        ) {
            AsyncImage(
                model = movie.backDropUrl,
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            ToolBar(title = "", navController = navController, isTransparent = true)
        }

        // Movie Title and Share Actions
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Text(
                text = movie.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextBlack,
                modifier = Modifier.weight(3f)
            )

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = TextBlack,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = TextBlack,
                    modifier = Modifier.size(20.dp)
                )
            }

        }

        // Rating and Other details
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = padding, end = padding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier
                .clickable { }
            ) {
                Icon(
                    modifier = Modifier.size(15.dp),
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = RedMain
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = movie.rating,
                    fontSize = 10.sp,
                    color = RedMain
                )
                Spacer(modifier = Modifier.width(2.dp))
                Icon(
                    modifier = Modifier.size(15.dp),
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = RedMain
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = movie.releaseDate,
                fontSize = 11.sp,
                color = TextBlack
            )
            Spacer(modifier = Modifier.width(8.dp))
            OtherDetailsText(
                text = movie.age
            )
            Spacer(modifier = Modifier.width(8.dp))
            OtherDetailsText(
                text = movie.language
            )
            Spacer(modifier = Modifier.width(4.dp))
            OtherDetailsText(
                text = movie.status
            )
        }

        // Play and Download Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            MIconButton(
                modifier = Modifier
                    .weight(1f)
                    .height(38.dp),
                title = "Play",
                backgroundColor = RedMain,
                borderColor = RedMain,
                icon = R.drawable.play_btn_small,
                textColor = Color.White,
                iconColor = Color.White,
                iconSize = 20.dp,
                textSize = 16.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            MIconButton(
                modifier = Modifier
                    .weight(1f)
                    .height(38.dp),
                title = "Download",
                backgroundColor = Color.White,
                borderColor = RedMain,
                icon = R.drawable.download_icon,
                textColor = RedMain,
                iconColor = RedMain,
                iconSize = 20.dp,
                textSize = 16.sp
            )
        }

        // Genre and Description
        Text(
            modifier = Modifier.padding(start = padding, end = padding),
            text = "Genre: ${movie.genre}",
            fontSize = 12.sp,
        )

        var descriptionEllipsized by remember {
            mutableStateOf(true)
        }

        ClickableText(
            modifier = Modifier.padding(
                start = padding,
                top = 8.dp,
                bottom = padding,
                end = padding
            ),
            text = AnnotatedString(movie.description, SpanStyle(fontSize = 12.sp)),
            onClick = {
                descriptionEllipsized = !descriptionEllipsized
            },
            maxLines = if (descriptionEllipsized) 3 else 8,
            overflow = if (descriptionEllipsized) TextOverflow.Ellipsis else TextOverflow.Visible
        )

        // Cast People
        LazyRow(
            contentPadding = PaddingValues(start = padding, end = padding),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.credits) { credit ->
                CastRow(credit)
            }
        }

        // Tabs: Trailers, More Like This, Comments
        TabLayout(
            poster = movie.backDropUrl,
            videos = state.trailers,
            similar = state.similar
        )

    }
}

@Composable
fun OtherDetailsText(
    text: String
) {
    Box(
        modifier = Modifier
            .height(18.dp)
            .border(1.dp, RedMain, RoundedCornerShape(4.dp))
            .clip(RoundedCornerShape(4.dp))
            .padding(top = 2.dp, bottom = 2.dp, start = 4.dp, end = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 9.sp, color = RedMain)

    }

}


@Composable
fun CastRow(credit: Credit) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = credit.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(verticalArrangement = Arrangement.Center) {
            Text(text = credit.name, fontSize = 10.sp)
            Text(text = credit.role, fontSize = 8.sp, color = LightGray)
        }


    }
}

@Composable
fun TabLayout(
    poster: String,
    videos: List<Video>,
    similar: List<Movie>
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    // Custom tab indicator
    val tabIndicatorHeight = 4.dp
    val tabIndicatorColor = RedMain
    val tabIndicatorCornerSize = 8.dp

    Column {
        TabRow(
            selectedTabIndex,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            backgroundColor = Color.White,
            contentColor = RedMain,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .height(tabIndicatorHeight)
                        .clip(RoundedCornerShape(tabIndicatorCornerSize))
                        .background(tabIndicatorColor)
                )
            }
        ) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 },
                text = { Text("Trailers") },
                unselectedContentColor = Color.DarkGray
            )
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 },
                text = { Text("More Like This") },
                unselectedContentColor = Color.DarkGray
            )
        }

        when (selectedTabIndex) {
            0 -> {
                TrailersTab(poster, videos)
            }
            1 -> {
                MoreLikeThisTab(similar)
            }
        }

    }
}

@Composable
fun TrailersTab(poster: String, videos: List<Video>) {
    LazyColumn(
        modifier = Modifier.height(800.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(videos) { video ->
            TrailersRow(poster, video)
        }
    }
}

@Composable
fun TrailersRow(poster: String, video: Video) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(170.dp)
                .height(130.dp)
        ) {
            AsyncImage(
                model = poster,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )

            Icon(
                painter = painterResource(id = R.drawable.play_btn_small),
                contentDescription = "",
                modifier = Modifier.align(Alignment.Center),
                tint = Color.White
            )

        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = video.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = video.date,
                fontSize = 12.sp
            )
        }

    }

}

@Composable
fun MoreLikeThisTab(similar: List<Movie>) {
    val padding = LocalDimensions.current.padding
    LazyVerticalGrid(
        modifier = Modifier.height(800.dp),
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(padding),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(similar) { movie ->
            MovieListItem(
                movie = movie,
                cardModifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {

            }
        }
    }
}

