package com.example.myapplication

import com.example.myapplication.data.TaskRepositoryImpl
import com.example.myapplication.domain.GetAllTask
import com.example.myapplication.domain.SwitchIsDone
import com.example.myapplication.domain.TaskRepository
import com.example.myapplication.ui.MainActivity
import com.example.myapplication.ui.TasksViewModelFactory

object InjectionUtil {
    private val taskRepo: TaskRepository = TaskRepositoryImpl()

    private val getAllTask: GetAllTask = GetAllTask(
        repository = taskRepo
    )

    private val switchIsDone = SwitchIsDone(
        repository = taskRepo
    )

    private val taskViewModeFactory = TasksViewModelFactory(
        getAllTask = getAllTask,
        switchIsDone = switchIsDone
    )

    fun inject(mainActivity: MainActivity) {
        mainActivity.viewModelFactory = taskViewModeFactory
    }
}