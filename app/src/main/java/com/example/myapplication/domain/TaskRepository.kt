package com.example.myapplication.domain

import androidx.lifecycle.LiveData

interface TaskRepository {
    fun getAll(): LiveData<List<Task>>
    fun getDone(): LiveData<List<Task>>
    fun getNotDone(): LiveData<List<Task>>

    fun switchIsDone(id: String)

}