package com.example.dailypulse.articles.di

import com.example.dailypulse.articles.ArticleService
import com.example.dailypulse.articles.ArticleUseCase
import com.example.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

val articleModule = module {

    single<ArticleService> { ArticleService(get()) }
    single<ArticleUseCase> { ArticleUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
}
