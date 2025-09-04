package com.example.dailypulse.articles

import com.example.dailypulse.BaseViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticlesViewModel() : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))
    val articlesState: StateFlow<ArticlesState> get() = _articlesState
    var useCase: ArticleUseCase

    init {

        val httpClient = setupHttpClient()
        val service = ArticleService(httpClient)
        useCase = ArticleUseCase(service)
        getArticles()
    }

    private fun setupHttpClient(): HttpClient {

        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
        return httpClient
    }

    private fun getArticles() {
        scope.launch {

            val articles = useCase.getArticles()

            _articlesState.emit(ArticlesState(articles))
        }
    }
}