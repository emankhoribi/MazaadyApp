package com.example.domain.entity.movies

data class MoviesResponse(
    val dates: Dates,
    val page: Int,
    val results: List<MoviesResult>,
    val total_pages: Int,
    val total_results: Int
)