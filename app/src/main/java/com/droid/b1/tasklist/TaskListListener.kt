package com.droid.b1.tasklist

interface TaskListListener {
    fun onClickDelete(task : Task) : (Task) -> Unit = {};
    fun onClickEdit(task: Task): (Task) -> Unit = {};
}