package com.droid.b1.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.droid.b1.data.Api
import com.droid.b1.data.TasksListViewModel
import com.droid.b1.databinding.FragmentTaskListBinding
import kotlinx.coroutines.launch
import java.util.*

class TaskListFragment : Fragment() {

    private var taskList = listOf(
        Task(id="id_1","Task 1", "Pouet pouet hahahaha"),
        Task("id_2","Task 2", "I DO WHATEVER I WANT"),
        Task("id_3","Task 3")
        ); //a list

    private val adapter = TaskListAdapter();
    private var binding : FragmentTaskListBinding? = null;
    private val viewModel: TasksListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmentTaskListBinding.inflate(inflater);
        //val rootView = inflater.inflate(R.layout.fragment_task_list,container,false);
        val rootView = binding!!.root;
        return rootView;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        //val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView);
        val recyclerView = binding?.recyclerView;
        recyclerView?.adapter=adapter;
        //adapter.submitList(taskList);
        binding?.floatingActionButton?.setOnClickListener(){
            val new_task = Task(UUID.randomUUID().toString(),"Task ${taskList.size+1}")
            taskList = taskList + new_task;
            refreshAdapter();
            viewModel.update(new_task)
            viewModel.add(new_task);
        }


        lifecycleScope.launch { // on lance une coroutine car `collect` est `suspend`
            suspendMethod();
        }

    }

    suspend fun suspendMethod(){
        viewModel.tasksStateFlow.collect { newList ->
            // cette lambda est executée à chaque fois que la liste est mise à jour dans le VM
            // -> ici, on met à jour la liste dans l'adapter
            taskList = newList;
            refreshAdapter();
        }
    }

    override fun onResume() {
        super.onResume()
        // Ici on ne va pas gérer les cas d'erreur donc on force le crash avec "!!"
        lifecycleScope.launch {
            mySuspendMethod()
        }
        viewModel.refresh() // on demande de rafraîchir les données sans attendre le retour directement
    }

    suspend fun mySuspendMethod(){
        val user = Api.userWebService.fetchUser().body()!!;
        binding?.userTextView?.text = user.name;
    }

    fun refreshAdapter(){
        adapter.submitList(taskList);
    }
}