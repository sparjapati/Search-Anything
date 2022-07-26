package com.sparjapati.searchAnything.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sparjapati.searchAnything.data.local.entity.WordInfoEntity
import com.sparjapati.searchAnything.domain.models.WordInfo

data class WordInfoDto(
    @field:SerializedName("phonetic")
    val phonetic: String,

    @field:SerializedName("phonetics")
    val phonetics: List<PhoneticsDto>,

    @field:SerializedName("word")
    val word: String,

    @field:SerializedName("meanings")
    val meanings: List<MeaningsDto>,

    @field:SerializedName("sourceUrls")
    val sourceUrls: List<String>,

    @field:SerializedName("license")
    val license: LicenseDto,
) {
    fun toWordInfo() = WordInfo(
        phonetic = phonetic,
        word = word,
        meanings = meanings.map { it.toMeaning() },
        sourceUrls = sourceUrls
    )

    fun toWordInfoEntity() = WordInfoEntity(
        word = word,
        phonetic = phonetic,
        sourceUrls = sourceUrls,
        meanings = meanings.map { it.toMeaning() }
    )
}

