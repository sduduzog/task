package dev.sduu.task.domain

import dev.sduu.task.data.TaskRepository

class CreateTaskUseCase(taskRepository: TaskRepository) {
    operator fun invoke() {
        println("CreateTaskUseCase")
    }
}