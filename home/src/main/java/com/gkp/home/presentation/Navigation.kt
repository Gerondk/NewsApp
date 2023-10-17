package com.gkp.home.presentation


import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gkp.core.Screens
import com.gkp.core.domain.NewsArticle

fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable(route = Screens.HomeScreen.route) {
        val homeViewModel = hiltViewModel<HomeViewModel>()
        val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
        Home(
            homeUiState,
            onNavigateToDetail = { article ->
                navController.currentBackStackEntry?.savedStateHandle?.set("Detail", article)
                navController.navigate(Screens.ArticleDetailScreen.route)
            }
        )
    }
    composable(route = Screens.ArticleDetailScreen.route) {
        val detailViewModel = hiltViewModel<DetailViewModel>()
        val newsArticle =
            navController.previousBackStackEntry?.savedStateHandle?.get<NewsArticle>("Detail")
        newsArticle?.let { article ->
            ArticleDetailScreen(
                newsArticle = article,
                onBackClicked = { navController.popBackStack() },
                onBookmark = { articleItem ->
                    detailViewModel.bookmarkNewsArticle(articleItem)
                    navController.popBackStack()
                }
            )
        }
    }
}