package com.sparjapati.searchAnything.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sparjapati.searchAnything.domain.models.Definition

data class DefinitionDto(

    @field:SerializedName("synonyms")
    val synonyms: List<String>,

    @field:SerializedName("antonyms")
    val antonyms: List<String>,

    @field:SerializedName("definition")
    val definition: String,

    @field:SerializedName("example")
    val example: String?,
) {
    fun toDefinition() = Definition(
        synonyms = synonyms,
        antonyms = antonyms,
        definition = definition,
        example = example
    )
}