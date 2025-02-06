package dev.sduu.task.android.di

import dev.sduu.task.android.ui.home.HomeViewModel
import dev.sduu.task.android.ui.task.TaskViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val androidModule = module {
    factoryOf(::HomeViewModel)
    factoryOf(::TaskViewModel)
}