package dev.sduu.task.android

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.sduu.task.android.di.androidModule
import dev.sduu.task.android.theme.AppTheme
import dev.sduu.task.android.ui.home.Home
import dev.sduu.task.android.ui.home.HomeRoute
import dev.sduu.task.android.ui.task.Task
import dev.sduu.task.android.ui.task.TaskScreen
import dev.sduu.task.di.appModule
import org.koin.compose.KoinApplication

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule + androidModule)
    }) {
        val navController = rememberNavController()
        AppTheme {
            Scaffold(
                topBar = { TopAppBar(title = { Text("Home") }) },
                contentWindowInsets = WindowInsets(0, 0, 0, 0)
            ) { scaffoldPadding ->
                NavHost(
                    navController, startDestination = Home, Modifier
                        .fillMaxSize()
                        .padding(scaffoldPadding)
                ) {
                    composable<Home> {
                        HomeRoute()
                    }
                    composable<Task> { TaskScreen() }
                }
            }
        }
    }
}