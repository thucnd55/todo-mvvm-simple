package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemBinding
import com.example.myapplication.domain.Task

class TaskAdapter(
    private val onItemCheckBoxClickListener: (String) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var items: List<Task> = emptyList()

    fun submitItems(value: List<Task>) {
        items = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = items[position]
        holder.bind(task, onItemCheckBoxClickListener)
    }


    class TaskViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task, onItemCheckBoxClickListener: (String) -> Unit) {
            binding.task123 = task
            binding.doneCheckbox.setOnClickListener {
                onItemCheckBoxClickListener(task.id)
            }
        }
    }
}