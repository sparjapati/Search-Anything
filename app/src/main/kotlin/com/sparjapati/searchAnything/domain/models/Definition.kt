package com.sparjapati.searchAnything.domain.models

data class Definition(
    val synonyms: List<String>,
    val antonyms: List<String>,
    val definition: String?,
    val example: String?,
)