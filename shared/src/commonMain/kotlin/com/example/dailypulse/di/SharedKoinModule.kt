package com.example.dailypulse.di

import com.example.dailypulse.articles.di.articleModule

val sharedKoinModules = listOf(
    articleModule,
    networkModule
)
