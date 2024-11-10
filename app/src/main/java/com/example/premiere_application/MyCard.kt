package com.example.premiere_application

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun MyCard(
    modifier: Modifier,
    route: String,
    chemin_img: String,
    titre: String,
    date: String?,
    navController: NavController
) {
    androidx.compose.material3.Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500$chemin_img",
                contentDescription = titre
            )
            Text(
                text = titre,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .width(180.dp),
            )
            Text(text = date?:"")
        }
    }
}