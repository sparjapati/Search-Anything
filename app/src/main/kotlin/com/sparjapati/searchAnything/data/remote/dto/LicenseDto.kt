package com.sparjapati.searchAnything.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LicenseDto(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("url")
    val url: String,
)
