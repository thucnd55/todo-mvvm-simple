package com.example.myapplication.domain

object TestData {
    val tasks = mutableListOf<Task>(
        Task(
            id = "1",
            content = "tasks 1",
            isDone = false
        ),
        Task(
            id = "2",
            content = "tasks 2",
            isDone = false
        ),
        Task(
            id = "3",
            content = "tasks 3",
            isDone = true
        )
    )
}