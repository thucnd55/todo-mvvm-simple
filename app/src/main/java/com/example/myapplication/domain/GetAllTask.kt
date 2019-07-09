package com.example.myapplication.domain

import androidx.lifecycle.LiveData

class GetAllTask constructor(
    private val repository: TaskRepository
) {

    /**
     * isDone
     *  null -> all
     *  done -> all done
     */
    operator fun invoke(isDone: Boolean? = null): LiveData<List<Task>> {
        val taskLiveData: LiveData<List<Task>> = when(isDone) {
            null -> repository.getAll()
            true -> repository.getDone()
            false -> repository.getNotDone()
        }
        return taskLiveData
    }
}