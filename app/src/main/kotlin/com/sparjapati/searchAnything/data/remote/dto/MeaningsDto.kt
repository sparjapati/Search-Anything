package com.sparjapati.searchAnything.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sparjapati.searchAnything.domain.models.Meaning

data class MeaningsDto(

    @field:SerializedName("synonyms")
    val synonyms: List<String>,

    @field:SerializedName("partOfSpeech")
    val partOfSpeech: String,

    @field:SerializedName("antonyms")
    val antonyms: List<String>,

    @field:SerializedName("definitions")
    val definitions: List<DefinitionDto>,
) {
    fun toMeaning() = Meaning(
        synonyms = synonyms,
        partOfSpeech = partOfSpeech,
        antonyms = antonyms,
        definitions = definitions.map { it.toDefinition() }
    )
}