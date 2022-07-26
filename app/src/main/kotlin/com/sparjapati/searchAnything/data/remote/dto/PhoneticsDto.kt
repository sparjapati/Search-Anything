package com.sparjapati.searchAnything.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sparjapati.searchAnything.domain.models.Phonetic

data class PhoneticsDto(

    @field:SerializedName("sourceUrl")
    val sourceUrl: String,

    @field:SerializedName("license")
    val license: LicenseDto,

    @field:SerializedName("text")
    val text: String,

    @field:SerializedName("audio")
    val audio: String,
) {
    fun toPhonetic() = Phonetic(
        sourceUrl = sourceUrl,
        text = text,
        audio = audio
    )
}