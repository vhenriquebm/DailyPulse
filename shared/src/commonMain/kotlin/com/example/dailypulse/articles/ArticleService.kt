package com.example.dailypulse.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticleService(private val httpClient: HttpClient) {

    private val country = "us"
    private val category = "business"
    private val apiKey = "93a11d5759794d06975ce9790509b794"

    suspend fun getArticles(): List<ArticleRaw> {
        val response: ArticleResponse =
            httpClient.get("https://newsapi.org/v2/top-headLines?country=$country&category$category&apiKey$apiKey")
                .body()
        return response.articles
    }
}