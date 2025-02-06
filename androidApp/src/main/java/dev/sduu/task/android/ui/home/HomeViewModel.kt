package dev.sduu.task.android.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.sduu.task.domain.CreateTaskUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ChecklistItem(val text: String, val checked: Boolean)

data class HomeUiState(
    val checklistItems: List<ChecklistItem>
)


class HomeViewModel(private val createTaskUseCase: CreateTaskUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState( emptyList()))
    val uiState = _uiState.asStateFlow()

    var searchInput by mutableStateOf("")
        private set

    fun onInputChange(input: String) {
        searchInput = input
    }

    fun onCreateChecklistItem() {
        searchInput = ""
        createTaskUseCase()
    }

    fun onCheckChanged(item: ChecklistItem, checked: Boolean) {}
}