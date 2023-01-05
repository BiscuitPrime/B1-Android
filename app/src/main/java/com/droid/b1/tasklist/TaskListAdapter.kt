package com.droid.b1.tasklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.droid.b1.R

object TaskDiffCallback : DiffUtil.ItemCallback<Task>(){
    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id==newItem.id;
    }
}

class TaskListAdapter(val listener: TaskListListener) : ListAdapter<Task,TaskListAdapter.TaskViewHolder>(TaskDiffCallback) {
    // on utilise `inner` ici afin d'avoir accès aux propriétés de l'adapter directement
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle = itemView.findViewById<TextView>(R.id.bear_name);
        val textViewImage = itemView.findViewById<ImageView>(R.id.imageTask);
        fun bind(task: Task) {
            textViewTitle.text = task.content;
            textViewImage.load(task.description);
            //We setup the delete button of the task list :
            val deleteButton = itemView.findViewById<ImageButton>(R.id.deleteTaskButton);
            deleteButton.setOnClickListener(){
                listener.onClickDelete(task)
            }
            //we set up the edit button of the task list :
            var editButton = itemView.findViewById<ImageButton>(R.id.editTaskButton);
            editButton.setOnClickListener(){
                listener.onClickEdit(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false);
        val taskViewHolder = TaskViewHolder(itemView);
        return taskViewHolder;
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(currentList[position]);
    }

    override fun getItemCount(): Int {
        return currentList.size;
    }
}
