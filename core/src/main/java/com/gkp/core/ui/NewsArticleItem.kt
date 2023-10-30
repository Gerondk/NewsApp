package com.gkp.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gkp.core.R
import com.gkp.core.domain.NewsArticle

@Composable
fun NewsArticleItem(
    modifier: Modifier = Modifier,
    newsArticle: NewsArticle,
    showDelete: Boolean = false,
    onDelete: (NewsArticle) -> Unit = {},
    onReadMore: () -> Unit = {}
) {
    Column(modifier = modifier) {
        Row {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(20.dp)),
                model = newsArticle.urlToImage ?: R.drawable.place_holder,
                contentDescription = newsArticle.title,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.place_holder)

            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = newsArticle.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = newsArticle.author ?: "", fontSize = 15.sp)
                Text(text = newsArticle.publishedDay ?: "", fontSize = 15.sp)
            }
            if (showDelete) {
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = { onDelete(newsArticle) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
        if (showDelete) {
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
                onClick = onReadMore
            ) {
                Text(text = stringResource(id = R.string.read_more))
            }
        }
    }
}
