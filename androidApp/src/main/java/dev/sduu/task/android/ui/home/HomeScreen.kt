package dev.sduu.task.android.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp


class HomeUiStatePreviewParameterProvider : PreviewParameterProvider<HomeUiState> {
    override val values: Sequence<HomeUiState>
        get() = sequenceOf(
            HomeUiState(
                listOf(ChecklistItem("First Item", false))
            )
        )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun HomeScreenPreview(@PreviewParameter(HomeUiStatePreviewParameterProvider::class) uiState: HomeUiState) {
    HomeScreen(uiState, "", {}, {}, { _, _ -> })
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    searchInput: String,
    onInputChange: (input: String) -> Unit,
    onCreate: () -> Unit,
    onCheckedChange: (item: ChecklistItem, checked: Boolean) -> Unit
) {
    TaskListScreen(
        uiState.checklistItems,
        searchInput,
        onInputChange = onInputChange,
        onCreate = onCreate,
        onCheckedChange = onCheckedChange,
    )
}

@Composable
private fun TaskListScreen(
    taskItems: List<ChecklistItem>,
    searchInput: String,
    onInputChange: (input: String) -> Unit,
    onCreate: () -> Unit,
    onCheckedChange: (item: ChecklistItem, checked: Boolean) -> Unit,
) {
    Column(
        modifier = Modifier.imePadding()
    ) {
        LazyColumn(
            Modifier.weight(1f)
        ) {
            items(taskItems) { item ->
                SingleTask(item, onCheckedChange)
            }
        }
        UserInput(searchInput, onInputChange = onInputChange, onCreate = onCreate)
        Spacer(
            modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars)
        )
    }
}

@Composable
fun SingleTask(
    item: ChecklistItem, onCheckedChange: (item: ChecklistItem, checked: Boolean) -> Unit
) {
    Row {
        Checkbox(
            item.checked,
            onCheckedChange = { onCheckedChange(item, it) },
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Text(
            item.text,
            textDecoration = if (item.checked) TextDecoration.LineThrough else TextDecoration.None,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1.0f)
                .alpha(if (item.checked) 0.5f else 1f)
        )
    }
}

@Composable
fun UserInput(text: String, onInputChange: (input: String) -> Unit, onCreate: () -> Unit) {
    val focusManager = LocalFocusManager.current
    Surface(tonalElevation = 1.dp) {
        Column(modifier = Modifier.padding(2.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(Modifier.padding(4.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                ) {
                    BasicTextField(
                        text,
                        onValueChange = onInputChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        textStyle = LocalTextStyle.current,
                        keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences),
                        keyboardActions = KeyboardActions(onDone = {
                            onCreate()
                            focusManager.clearFocus()
                        }),
                        singleLine = true,
                    )
                    if (text.isEmpty()) Text(
                        "What's on your mind?", modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .alpha(0.4f)
                    )
                }
//                IconButton(
//                    onClick = onCreate, modifier = Modifier.align(Alignment.CenterVertically)
//                ) {
//                    Icon(
//                        Icons.AutoMirrored.Rounded.KeyboardArrowRight,
//                        contentDescription = "",
//                        modifier = Modifier.alpha(0.6f)
//                    )
//                }
            }
        }
    }
}