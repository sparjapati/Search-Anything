package com.sparjapati.searchAnything.presentation

import com.sparjapati.searchAnything.domain.models.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false,
)
