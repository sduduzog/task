package dev.sduu.task.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import dev.sduu.task.android.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                TaskContent()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_9")
@Composable
fun TaskContent(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Home") }) },
        modifier = modifier
            .imePadding(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { scaffoldPadding ->
        var text by remember { mutableStateOf("") }
        Column(
            Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
                .consumeWindowInsets(scaffoldPadding)
                .systemBarsPadding()
        ) {
            LazyColumn(
                modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(1) { i ->
                    var checked by remember { mutableStateOf(false) }
                    Row {
                        Checkbox(
                            checked,
                            onCheckedChange = { checked = it },
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Text(
                            "Checklist item ${i + 1}",
                            textDecoration = if (checked) TextDecoration.LineThrough else TextDecoration.None,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(1.0f)
                                .alpha(if (checked) 0.5f else 1f)
                        )
                    }
                }
            }
            Box(modifier = Modifier.padding(16.dp)) {
                Surface(
                    tonalElevation = 1.dp,
                    modifier = Modifier.clip(RoundedCornerShape(22.dp))
                ) {
                    Column {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Spacer(Modifier.padding(4.dp))
                            Box(modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)) {
                                BasicTextField(
                                    text,
                                    textStyle = LocalTextStyle.current,
                                    onValueChange = { text = it },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                )
                                if (text.isEmpty()) Text(
                                    "What's on your mind?",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)

                                        .alpha(0.4f)
                                )
                            }
                            IconButton(
                                onClick = {},
                                modifier = Modifier.align(Alignment.CenterVertically)
                            ) {
                                Icon(
                                    Icons.Rounded.KeyboardArrowRight,
                                    contentDescription = "",
                                    modifier = Modifier.alpha(0.6f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}