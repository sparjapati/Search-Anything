package com.sparjapati.searchAnything.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.sparjapati.searchAnything.data.utils.JsonParser
import com.sparjapati.searchAnything.domain.models.Meaning

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser,
) {
    @TypeConverter
    fun fromMeaningJson(json: String): List<Meaning> {
        return jsonParser.fromJson<List<Meaning>>(
            json,
            object : TypeToken<List<Meaning>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<List<Meaning>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromListJson(json: String): List<String> {
        return jsonParser.fromJson<List<String>>(
            json,
            object : TypeToken<List<String>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toListJson(list: List<String>): String {
        return jsonParser.toJson(
            list,
            object : TypeToken<List<String>>() {}.type
        ) ?: "[]"
    }
}