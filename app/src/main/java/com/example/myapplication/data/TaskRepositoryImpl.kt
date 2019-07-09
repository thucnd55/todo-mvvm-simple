package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.myapplication.domain.Task
import com.example.myapplication.domain.TaskRepository

class TaskRepositoryImpl : TaskRepository {


    override fun switchIsDone(id: String) {
        val foundTask = data.find { task ->
            task.id == id
        }
        if (foundTask == null)
            throw IllegalArgumentException()

        val newTask = foundTask.copy(isDone = !foundTask.isDone)
        val index = data.indexOf(foundTask)
        data[index] = newTask
        source.value = data
    }

    var data = arrayListOf<Task>(
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

    private val source = MutableLiveData<List<Task>>().apply { value = data  }

    override fun getAll(): LiveData<List<Task>> {
        return source
    }

    override fun getDone(): LiveData<List<Task>> {
        return MediatorLiveData<List<Task>>().apply {
            addSource(source) { newList ->
                val tasks = newList.filter { task ->
                    task.isDone == true
                }
                value = tasks
            }
        }
    }

    override fun getNotDone(): LiveData<List<Task>> {
        return MediatorLiveData<List<Task>>().apply {
            addSource(source) { newList ->
                val tasks = newList.filter { task ->
                    task.isDone == false
                }
                value = tasks
            }
        }
    }

}