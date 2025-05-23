package com.example.domain.entity.movies

data class MovieListResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)