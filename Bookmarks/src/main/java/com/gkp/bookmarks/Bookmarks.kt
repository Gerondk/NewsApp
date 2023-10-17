package com.gkp.bookmarks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gkp.bookmarks.components.BookmarkedArticlesList
import com.gkp.core.domain.NewsArticle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Bookmarks( uiState: BookmarksUiState, onDeleteBookmark: (NewsArticle) -> Unit) {
     Scaffold(
         topBar = {
             TopAppBar(
                 title = { Text(text = "Bookmarks") },
//                 actions = {
//                     IconButton(onClick = { /*TODO*/ }) {
//                         Icon(
//                             imageVector = Icons.Default.Delete,
//                             contentDescription = "Delete"
//                         )
//
//                     }
//                 }
             )
         },

     ) { paddingValues ->
         Box(
             modifier = Modifier
                 .fillMaxSize()
                 .padding(paddingValues),
         ){
             when (uiState) {
                 BookmarksUiState.Loading -> {
                     CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                 }
                 is BookmarksUiState.Success -> {
                     BookmarkedArticlesList(
                         articles = uiState.articles,
                         onDeleteBookmark = onDeleteBookmark
                     )
                 }
             }
         }
     }
}