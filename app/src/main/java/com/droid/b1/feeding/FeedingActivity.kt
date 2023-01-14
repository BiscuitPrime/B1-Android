package com.droid.b1.feeding

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.droid.b1.feeding.ui.theme.B1AndroidTheme
import com.droid.b1.tasklist.Task

class FeedingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val task = intent.getSerializableExtra("Task") as Task
        //System.out.println("Feeding ACTIVITY : "+task);
        setContent {
            B1AndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Feeding(task, {task ->
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
fun Feeding(initialTask: Task, onReturn: (Task) -> Unit) {
    var startingCatAmount = 5;
    val rnds = (300..499).random()
    val rnds2 = (300..499).random()
    var catAmount by remember { mutableStateOf(startingCatAmount) }
    Column(modifier = Modifier.padding(16.dp), Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel), contentDescription = null,
            modifier = Modifier.clickable { onReturn(initialTask) })
        Text(text = "Feed the bear", textAlign = TextAlign.Center, style = MaterialTheme.typography.h3)
        Text(text = "Cats remaining : " + catAmount, textAlign = TextAlign.Center, style = MaterialTheme.typography.h4)
        AsyncImage(model = "https://placekitten.com/"+rnds+"/"+rnds2,
            modifier = Modifier.clickable(onClick = {
                // feed the bear
                initialTask.addHunger(10)
                catAmount--
                if (catAmount == 0) {
                    onReturn(initialTask)
                }
                Log.d("Feeding ", "Cat was clicked")
            }),
            contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun FeedingPreview() {
    B1AndroidTheme {
        // useless
    }
}