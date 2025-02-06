package dev.sduu.task.di

import dev.sduu.task.domain.CreateTaskUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val appModule = module {
    factoryOf(::CreateTaskUseCase)
}