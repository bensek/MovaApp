package com.amalitech.movaapp.ft_home.home

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.amalitech.movaapp.R
import com.amalitech.movaapp.core.components.cornerSize
import com.amalitech.movaapp.domain.model.Movie
import com.amalitech.movaapp.ui.theme.LocalDimensions
import com.amalitech.movaapp.ui.theme.RedMain
import com.amalitech.movaapp.ui.theme.TextBlack

val movie = Movie("Dr. Strange 2", "Action, Science, Fiction", 9.8,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzJW2tINWyydVxjIIIKrDC3iHM-pX4_Pd9eg&usqp=CAU")

@Composable
fun HomeScreen() {

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        FeaturedMovie(movie = movie)

        SectionList(title = "Top 10 Movies This Week", moviesList = emptyList() )

        SectionList(title = "New Releases", moviesList = emptyList() )

        SectionList(title = "Top 10 Movies This Week", moviesList = emptyList() )

        SectionList(title = "New Releases", moviesList = emptyList() )
    }

}

@Composable
fun FeaturedMovie(movie: Movie) {
    Box(
        modifier = Modifier
            .height(280.dp)
    ) {
//        AsyncImage(
//            model = movie.image,
//            contentDescription = movie.title,
//            modifier = Modifier.fillMaxSize()
//        )
        Image(
            painter = painterResource(id = com.amalitech.movaapp.R.drawable.movie),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalDimensions.current.padding)
        ) {
            Image(
                painter = painterResource(id = com.amalitech.movaapp.R.drawable.logo),
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
                fontSize = 16.sp,
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
            items(10) {
                val movie = Movie("Dr. Strange 2", "Action, Science, Fiction", 9.5,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzJW2tINWyydVxjIIIKrDC3iHM-pX4_Pd9eg&usqp=CAU")

                SectionListItem(movie = movie)
            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    val movie = Movie("Dr. Strange 2", "Action, Science, Fiction", 9.5,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzJW2tINWyydVxjIIIKrDC3iHM-pX4_Pd9eg&usqp=CAU")

    SectionListItem(movie = movie)
    
}

@Composable
fun SectionListItem(movie: Movie) {
    
    Box() {


        Card(
            modifier = Modifier
                .width(140.dp)
                .height(200.dp),
            shape = RoundedCornerShape(4.dp)

        ) {

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.movie),
                contentDescription = movie.title,
                contentScale = ContentScale.Crop
            )

        }

//        Text(
//            modifier = Modifier
//                .width(20.dp)
//                .background(RedMain)
//                .clip(RoundedCornerShape(2.dp)),
//            text = movie.rating.toString(),
//            color = Color.White,
//            fontSize = 10.sp
//        )

    }
}