package com.droid.b1.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droid.b1.detail.ui.theme.B1AndroidTheme
import com.droid.b1.tasklist.Task
import org.w3c.dom.Text
import java.util.*

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
    Column(modifier = Modifier.padding(16.dp), Arrangement.spacedBy(16.dp)) {
        Text(text = "Task Detail", style = MaterialTheme.typography.h1)
        OutlinedTextField(text = "title")
        OutlinedTextField(text = "description")
        Button(onClick = {
            val newTask = Task(id = UUID.randomUUID().toString(), "New Task !","Pouet");
            var task by remember { mutableStateOf(Task()) } // faire les imports suggérés par l'IDE
            onValidate(newTask);
        }) {}
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    B1AndroidTheme {
        //Detail()
    }
}