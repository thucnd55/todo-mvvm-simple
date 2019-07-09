package com.example.myapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.myapplication.domain.GetAllTask
import com.example.myapplication.domain.SwitchIsDone
import com.example.myapplication.domain.Task


/**
 * Created by kietnlt on 09 Jul 2019.
 */
class TaskViewModel constructor(
    private val getAllTask: GetAllTask,
    private val switchIsDone: SwitchIsDone
) : ViewModel() {
    // 0 -> all
    // 1 -> isDOne
    // 2 -> not done
    private val _filter = MutableLiveData<Int>().apply { value = 0 }
    val filter: LiveData<Int>
        get() = _filter

    val tasks: LiveData<List<Task>> = filter.switchMap { filter ->
        val isDone =  when(filter) {
            0 -> null
            1 -> true
            2 -> false
            else -> throw IllegalArgumentException()
        }
        getAllTask(isDone)
    }

    fun onItemClick(id: String) {
        switchIsDone(id)

    }


    fun onFilterChange(filter: Int) {
        _filter.value = filter
    }
}


fun main() {

}