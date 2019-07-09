package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.InjectionUtil
import com.example.myapplication.R


// ViewModel + LiveData + DataBinding
class MainActivity : AppCompatActivity() {

    lateinit var viewModelFactory: TasksViewModelFactory


    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: TaskViewModel

    val myAdapter = TaskAdapter(
        onItemCheckBoxClickListener = { taskId ->
            viewModel.onItemClick(taskId)
        }
    )

    private lateinit var filterSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        InjectionUtil.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskViewModel::class.java)


        recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view).apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        filterSpinner = findViewById<Spinner>(R.id.filter_spinner)
        filterSpinner.apply {
            adapter = ArrayAdapter<String>(
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                arrayListOf(
                    "All",
                    "Done",
                    "Not done"
                )
            )
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {}

                override fun onItemSelected(a: AdapterView<*>?, v: View?, position: Int, l: Long) {
                    viewModel.onFilterChange(position)
                }

            }
        }

        viewModel.filter.observe(this) { newFilter ->

        }

        viewModel.tasks.observe(this) { newList ->
            recyclerView.post {
                myAdapter.submitItems(newList)
            }
        }
    }


}
