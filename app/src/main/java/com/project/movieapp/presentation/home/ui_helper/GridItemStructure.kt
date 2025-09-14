package com.project.movieapp.presentation.home.ui_helper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage



@Composable
fun GridItemStructure(
    posterUrl:String? = null,
    releaseDate:String? = null,
    title :String,
    isAdult:Boolean = false,
    onClick: () -> Unit



) {
    Card(
        onClick =onClick ,
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)   // 🔹 fixed height for uniform grid
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Image (top half)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                AsyncImage(
                    model = posterUrl,
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()


                )
if (isAdult){
                Text(
                    "A",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(Color.Red)
                        .padding(5 .dp)
                )
}
            }
            Text(
                releaseDate.toString(),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(3.dp)
            )


                Text(
                    title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )

        }
    }
}
