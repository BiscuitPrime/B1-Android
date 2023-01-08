package com.droid.b1.tasklist

interface TaskListListener {
    fun onClickDelete(task: Task)
    fun onClickEdit(task: Task)
    fun onClickFeed(task: Task)
}