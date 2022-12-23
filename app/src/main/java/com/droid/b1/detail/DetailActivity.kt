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
        setContent {
            B1AndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Detail({task ->
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
fun Detail(onValidate: (Task) -> Unit) {
    var task by remember { mutableStateOf(Task("pif", "paf", "pouf")) }
    var text by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp), Arrangement.spacedBy(16.dp)) {
        Text(text = "Task Detail", style = MaterialTheme.typography.h1)
        OutlinedTextField(text, {text = it})
        OutlinedTextField(description, {description = it})
        Button(onClick = {
            task.content = text;
            task.description = description;
            onValidate(task);
        }) {
            // adding text on below line.
            Text("Create Task")
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