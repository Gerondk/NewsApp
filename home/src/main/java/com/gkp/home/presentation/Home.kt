package com.gkp.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gkp.core.domain.NewsArticle
import com.gkp.core.ui.ErrorScreen
import com.gkp.home.presentation.component.NewsArticleList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Home(
    homeUiState: HomeUiState,
    onNavigateToDetail: (NewsArticle) -> Unit,
    onError: () -> Unit,
    onSelectedSource: (String) -> Unit,
    onDarkTheme: (Boolean?) -> Unit
) {
    var showSettingDialog by remember {
        mutableStateOf(false)
    }
    var darkTheme by remember {
        mutableStateOf<Boolean?>(null)
    }
    if (showSettingDialog) {
        AlertDialog(
            onDismissRequest = { showSettingDialog = false },
            confirmButton = {
                Button(onClick = { showSettingDialog = false }) {
                    Text(text = "OK")
                }
            },
            title = { Text(text = "Setting") },
            text = {
                Column(modifier = Modifier.selectableGroup()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = darkTheme == null,
                            onClick = {
                                darkTheme = null
                                onDarkTheme(null)
                            }
                        )
                        Text(text = "System")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = darkTheme == false,
                            onClick = {
                                darkTheme = false
                                onDarkTheme(false)
                            }
                        )
                        Text(text = "Light")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = darkTheme == true,
                            onClick = {
                                darkTheme = true
                                onDarkTheme(true)
                            }
                        )
                        Text(text = "Dark")
                    }
                }
            }
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "News",
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            showSettingDialog = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())

        ) {
            when (homeUiState) {
                is HomeUiState.Error -> {
                    ErrorScreen(
                        modifier = Modifier.align(Alignment.Center),
                        message = homeUiState.errorMessage ?: "",
                        buttonText = "retry",
                        onError = onError
                    )
                }

                HomeUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center)
                    )
                }

                is HomeUiState.Success -> {
                    NewsArticleList(
                        articles = homeUiState.source
                            ?.let { homeUiState.sourceArticles } ?: homeUiState.articles,
                        onNavigateToDetail = onNavigateToDetail,
                        sources = homeUiState.sources,
                        onSelectedSource = onSelectedSource
                    )
                }
            }
        }
    }
}
