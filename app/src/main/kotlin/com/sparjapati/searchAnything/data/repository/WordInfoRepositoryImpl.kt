package com.sparjapati.searchAnything.data.repository

import com.sparjapati.core.utils.Resource
import com.sparjapati.core.utils.TextUtil
import com.sparjapati.searchAnything.R
import com.sparjapati.searchAnything.data.local.WordInfoDao
import com.sparjapati.searchAnything.data.remote.DictionaryApi
import com.sparjapati.searchAnything.domain.models.WordInfo
import com.sparjapati.searchAnything.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao,
) : WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val localWordInfo = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Loading(localWordInfo))

        try {
            val remoteWordInfo = api.getWordData(word)
            dao.deleteWordInfos(remoteWordInfo.map { it.word })
            dao.insertWordInfos(remoteWordInfo.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error(TextUtil.StringResource(R.string.something_wrong)))
        } catch (e: IOException) {
            emit(Resource.Error(TextUtil.StringResource(R.string.no_internet_connection)))
        }

        val newWordInfo = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfo))
    }
}