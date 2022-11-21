package com.droid.b1.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.droid.b1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class TaskListFragment : Fragment() {

    private var taskList = listOf(
        Task(id="id_1","Task 1", "Pouet pouet hahahaha"),
        Task("id_2","Task 2", "I DO WHATEVER I WANT"),
        Task("id_3","Task 3")
        ); //a list

    private val adapter = TaskListAdapter();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val rootView = inflater.inflate(R.layout.fragment_task_list,container,false);
        adapter.currentList = taskList;
        return rootView;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView);
        val fab = view.findViewById<FloatingActionButton>(R.id.floatingActionButton);

        fab.setOnClickListener(){
            val new_task = Task(UUID.randomUUID().toString(),"Task ${taskList.size+1}")
            taskList = taskList + new_task;
            refreshAdapter();
        }

        recyclerView.adapter=adapter;
    }

    fun refreshAdapter(){
        adapter.currentList=taskList;
        adapter.notifyDataSetChanged();
    }
}