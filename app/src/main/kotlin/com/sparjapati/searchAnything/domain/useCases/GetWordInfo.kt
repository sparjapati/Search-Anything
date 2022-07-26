package com.sparjapati.searchAnything.domain.useCases

import com.sparjapati.core.utils.Resource
import com.sparjapati.searchAnything.domain.models.WordInfo
import com.sparjapati.searchAnything.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repository: WordInfoRepository,
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank())
            return flow { }
        return repository.getWordInfo(word)
    }
}