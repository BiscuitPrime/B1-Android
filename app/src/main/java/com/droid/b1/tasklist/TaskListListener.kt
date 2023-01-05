package com.droid.b1.tasklist

interface TaskListListener {
    fun onClickDelete(task: Task) : Unit
    fun onClickEdit(task: Task) : Unit
}