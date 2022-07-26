package com.sparjapati.searchAnything.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sparjapati.searchAnything.data.local.entity.WordInfoEntity

data class WordInfoDto(
    @field:SerializedName("phonetic")
    val phonetic: String = "",

    @field:SerializedName("phonetics")
    val phonetics: List<PhoneticsDto> = emptyList(),

    @field:SerializedName("word")
    val word: String = "",

    @field:SerializedName("meanings")
    val meanings: List<MeaningsDto> = emptyList(),

    @field:SerializedName("sourceUrls")
    val sourceUrls: List<String> = emptyList(),

    @field:SerializedName("license")
    val license: LicenseDto,
) {
    fun toWordInfoEntity() = WordInfoEntity(
        word = word,
        phonetic = phonetic,
        sourceUrls = sourceUrls,
        meanings = meanings.map { it.toMeaning() }
    )
}

