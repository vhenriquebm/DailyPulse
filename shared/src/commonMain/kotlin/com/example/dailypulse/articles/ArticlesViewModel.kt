package com.example.dailypulse.articles

import com.example.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel() : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState(loading = true))

    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {

            delay(1500)

            _articlesState.emit(ArticlesState(error = "Something went wrong"))


            delay(1500)

            val articles = fetchArticles()

            _articlesState.emit(ArticlesState(articles))
        }
    }

    suspend fun fetchArticles(): List<Article> = mockArticles

    private val mockArticles = listOf(
        Article(
            title = "DailyPulse 1.0 Released",
            desc = "Track device info and read tech news with a clean UI.",
            date = "2025-09-02",
            imageUrl = "https://picsum.photos/seed/dailypulse/800/400"
        ),
        Article(
            title = "Compose Multiplatform: Best Practices",
            desc = "Share logic across platforms while keeping native UIs.",
            date = "2025-08-30",
            imageUrl = "https://picsum.photos/seed/compose/800/400"
        ),
        Article(
            title = "Kotlin Coroutines 1.9 Highlights",
            desc = "Improvements to structured concurrency and performance.",
            date = "2025-08-25",
            imageUrl = "https://picsum.photos/seed/coroutines/800/400"
        )
    )
}