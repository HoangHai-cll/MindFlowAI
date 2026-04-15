package vn.humg.hai.mindflowai.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.humg.hai.mindflowai.data.repository.MindMapRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MindMapRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun generateMindMap(topicName: String) {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            repository.createNewMindMap(topicName)
                .onSuccess { topicId ->
                    _uiState.value = HomeUiState.Success(topicId)
                }
                .onFailure { error ->
                    _uiState.value = HomeUiState.Error(error.message ?: "Unknown error")
                }
        }
    }
    
    fun resetState() {
        _uiState.value = HomeUiState.Idle
    }
}

sealed class HomeUiState {
    object Idle : HomeUiState()
    object Loading : HomeUiState()
    data class Success(val topicId: Long) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
