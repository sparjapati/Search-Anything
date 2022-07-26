package com.sparjapati.searchAnything.domain.repository

import com.sparjapati.core.utils.Resource
import com.sparjapati.searchAnything.domain.models.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}