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

class TaskListAdapter : ListAdapter<Task,TaskListAdapter.TaskViewHolder>(TaskDiffCallback) {

    // Déclaration de la variable lambda dans l'adapter:
    var onClickDelete: (Task) -> Unit = {}

    // on utilise `inner` ici afin d'avoir accès aux propriétés de l'adapter directement
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle = itemView.findViewById<TextView>(R.id.task_title);
        val textViewDescription = itemView.findViewById<TextView>(R.id.task_description);
        val textViewImage = itemView.findViewById<ImageView>(R.id.imageTask);
        fun bind(task: Task) {
            textViewTitle.text = task.content;
            textViewDescription.text=task.description;
            textViewImage.load("https://placebear.com/200/300");
        }
        val deleteButton = itemView.findViewById<ImageButton>(R.id.deleteTaskButton);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false);
        val taskViewHolder = TaskViewHolder(itemView);
        taskViewHolder.deleteButton.setOnClickListener(View.OnClickListener { onClickDelete(task) })
        return taskViewHolder;
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(currentList[position]);
    }

    override fun getItemCount(): Int {
        return currentList.size;
    }
}
