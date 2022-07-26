package com.sparjapati.searchAnything.domain.models


data class Meaning(
    val synonyms: List<String>,
    val partOfSpeech: String,
    val antonyms: List<String>,
    val definitions: List<Definition>,
)