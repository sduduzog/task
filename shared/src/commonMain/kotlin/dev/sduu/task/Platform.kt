package dev.sduu.task

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform