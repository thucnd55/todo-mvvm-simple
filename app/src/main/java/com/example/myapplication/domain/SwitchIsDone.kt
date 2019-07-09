package com.example.myapplication.domain

class SwitchIsDone(
    private val repository: TaskRepository
) {
    operator fun invoke(taskId: String) {
        repository.switchIsDone(taskId)
    }
}