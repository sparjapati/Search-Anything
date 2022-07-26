package com.sparjapati.searchAnything.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sparjapati.searchAnything.domain.models.Meaning
import com.sparjapati.searchAnything.domain.models.WordInfo

@Entity
data class WordInfoEntity(
    val word: String,
    val phonetic: String?,
    val sourceUrls: List<String>,
    val meanings: List<Meaning>,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
) {
    fun toWordInfo() = WordInfo(
        phonetic = phonetic ?: "",
        word = word,
        meanings = meanings,
        sourceUrls = sourceUrls
    )
}