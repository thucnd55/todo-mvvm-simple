package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.GetAllTask
import com.example.myapplication.domain.SwitchIsDone


/**
 * Created by kietnlt on 09 Jul 2019.
 */
class TasksViewModelFactory constructor(
    private val getAllTask: GetAllTask,
    private val switchIsDone: SwitchIsDone
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(
            getAllTask = getAllTask,
            switchIsDone = switchIsDone
        ) as T
    }
}