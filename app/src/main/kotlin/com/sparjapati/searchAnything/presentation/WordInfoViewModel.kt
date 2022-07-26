package com.sparjapati.searchAnything.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparjapati.core.utils.Resource
import com.sparjapati.core.utils.TextUtil
import com.sparjapati.searchAnything.domain.useCases.DictionaryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val dictionaryUseCases: DictionaryUseCases,
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private var _wordInfoState = mutableStateOf(WordInfoState())
    val wordInfoState: State<WordInfoState> = _wordInfoState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    sealed class UiEvent {
        data class ShowSnackBar(val message: TextUtil) : UiEvent()
    }

    fun onSearch(query: String) {
        _searchQuery.value = query.trim()
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            dictionaryUseCases.getWordInfo(query.trim()).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _wordInfoState.value = wordInfoState.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _wordInfoState.value = wordInfoState.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _wordInfoState.value = wordInfoState.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(UiEvent.ShowSnackBar(result.message ?: TextUtil.DynamicString("Unknown Error occurred")))
                    }
                }
            }.launchIn(this)
        }
    }

    fun clearDatabase() {
        viewModelScope.launch {
            try {
                dictionaryUseCases.clearWordsInfos()
                _eventFlow.emit(UiEvent.ShowSnackBar(TextUtil.DynamicString("Database cleared")))
            } catch (e: IOException) {
                _eventFlow.emit(UiEvent.ShowSnackBar(TextUtil.DynamicString(e.message ?: "Unknown Error occurred")))
            }
        }
    }

}