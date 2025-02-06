package dev.sduu.task.android.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel


@Serializable
object Home

@Composable
fun HomeRoute(viewModel: HomeViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchInput = viewModel.searchInput
    HomeScreen(
        uiState,
        searchInput,
        onInputChange = { viewModel.onInputChange(it) },
        onCreate = { viewModel.onCreateChecklistItem() },
        onCheckedChange = { item, checked -> viewModel.onCheckChanged(item, checked) }
    )
}