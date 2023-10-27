package com.gkp.core

private const val HOME = "Home"
private const val BOOKMARKS = "Bookmarks"

private const val ARTICLE_DETAIL = "ArticleDetails"

sealed class Screens(val route: String) {
    object HomeScreen : Screens(HOME)
    object BookmarksScreen : Screens(BOOKMARKS)
    object ArticleDetailScreen : Screens(ARTICLE_DETAIL)
}
