package com.droid.b1.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.droid.b1.R

class TaskListFragment : Fragment() {

    private var taskList = listOf("Task 1","Task 2", "Task 3"); //a list

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
        recyclerView.adapter=adapter;
    }
}