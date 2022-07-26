package com.sparjapati.searchAnything.domain.useCases

import com.sparjapati.searchAnything.domain.repository.WordInfoRepository

class ClearWordsInfos(
    private val repository: WordInfoRepository,
) {
    suspend operator fun invoke() {
        repository.clearWordInfos()
    }
}