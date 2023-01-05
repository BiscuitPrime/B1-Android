package com.droid.b1.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droid.b1.tasklist.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TasksListViewModel : ViewModel() {
    private val webService = Api.tasksWebService

    public val tasksStateFlow = MutableStateFlow<List<Task>>(emptyList())

    fun refresh() {
        viewModelScope.launch {
            val response = webService.fetchTasks() // Call HTTP (opération longue)
            if (!response.isSuccessful) { // à cette ligne, on a reçu la réponse de l'API
                Log.e("Network", "Error in REFRESH: ${response.message()}")
                return@launch
            }
            val fetchedTasks = response.body()!!
            tasksStateFlow.value = fetchedTasks // on modifie le flow, ce qui déclenche ses observers
        }
    }

    //Function that will update the task on the service
    fun update(task: Task) {
        viewModelScope.launch {
            val response = webService.update(task);
            if (!response.isSuccessful) {
                Log.e("Network", "Error in UPDATE: ${response.raw()}")
                return@launch
            }
            val updatedTask = response.body()!!
            tasksStateFlow.value = tasksStateFlow.value.map { if (it.id == updatedTask.id) updatedTask else it }
        }
    }

    //function that will add a task on the service
    fun add(task: Task) {
        viewModelScope.launch {
            val response = webService.create(task);
            if (!response.isSuccessful) {
                Log.e("Network", "Error in ADD ${response.raw()}")
                return@launch
            }
            val addedTask = response.body()!!
            tasksStateFlow.value = tasksStateFlow.value+addedTask;
        }
    }

    //function that will remove a task from the service
    fun remove(task: Task) {
        viewModelScope.launch {
            val response = webService.delete(task.id);
            if (!response.isSuccessful) {
                Log.e("Network", "Error in REMOVE ${response.raw()}")
                return@launch
            }

            tasksStateFlow.value = tasksStateFlow.value -task;
        }
    }
}