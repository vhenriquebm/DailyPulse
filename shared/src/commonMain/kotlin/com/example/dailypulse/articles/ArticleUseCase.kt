package com.example.dailypulse.articles

class ArticleUseCase(private val service: ArticleService) {

    suspend fun getArticles(): List<Article> {
        val articleRaw = service.getArticles()
        return mapArticles(articleRaw = articleRaw)
    }

    private fun mapArticles(articleRaw: List<ArticleRaw>): List<Article> = articleRaw.map { raw ->
        Article(
            raw.title,
            raw.desc ?: "Click to find out more",
            raw.date,
            raw.imageUrl ?: ""
        )
    }
}