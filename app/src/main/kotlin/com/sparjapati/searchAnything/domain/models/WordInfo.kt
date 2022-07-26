package com.sparjapati.searchAnything.domain.models

data class WordInfo(
    val phonetic: String,
    val word: String,
    val meanings: List<Meaning>,
    val sourceUrls: List<String>,
)

