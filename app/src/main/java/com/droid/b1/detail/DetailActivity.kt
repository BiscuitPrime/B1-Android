package com.droid.b1.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droid.b1.detail.ui.theme.B1AndroidTheme
import com.droid.b1.tasklist.Task

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val task = intent.getSerializableExtra("Task") as Task?
        System.out.println("DETAIL ACTIVITY : "+task);
        setContent {
            B1AndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Detail(task, {task ->
                        intent.putExtra("task",task);
                        setResult(RESULT_OK, intent);
                        finish();
                    })
                }
            }
        }
    }
}

@Composable
fun Detail(initialTask: Task?, onValidate: (Task) -> Unit) {
    if(initialTask!=null) //if there's a task inputted, we're in edition
    {
        var task by remember { mutableStateOf(Task("pif", "pim","paf")) }
        var id by remember { mutableStateOf(initialTask.id)}
        var url by remember { mutableStateOf(initialTask.description)}
        var text by remember { mutableStateOf(initialTask.content) }

        Column(modifier = Modifier.padding(16.dp), Arrangement.spacedBy(16.dp)) {
            Text(text = "Task Detail", style = MaterialTheme.typography.h1)
            OutlinedTextField(text, {text = it})
            Button(onClick = {
                task.id=id;
                task.content = text;
                task.description = url;
                onValidate(task);
            }) {
                // adding text to the button
                Text("Edit Bear")
            }
        }
    }
    else //otherwise, we're creating a task out of thin air
    {
        var task by remember { mutableStateOf(Task("pif","pim","paf")) }
        var text by remember { mutableStateOf("Bear Name") }

        Column(modifier = Modifier.padding(16.dp), Arrangement.spacedBy(16.dp)) {
            Text(text = "Task Detail", style = MaterialTheme.typography.h1)
            OutlinedTextField(text, {text = it})
            //OutlinedTextField(description, {description = it})
            Button(onClick = {
                val rnds = (300..499).random()
                val rnds2 = (300..499).random()
                task.description="https://placebear.com/"+rnds+"/"+rnds2;
                task.content = text;
                onValidate(task);
            }) {
                // adding text to the button
                Text("Create Bear")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    B1AndroidTheme {
        //Detail()
    }
}