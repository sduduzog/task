package dev.sduu.task.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config : KoinAppDeclaration? = null) {
    startKoin {
        modules(appModule)
    }
}