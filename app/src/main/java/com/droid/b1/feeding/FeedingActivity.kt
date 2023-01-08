package com.droid.b1.feeding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.droid.b1.feeding.ui.theme.B1AndroidTheme
import com.droid.b1.tasklist.Task

class FeedingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val task = intent.getSerializableExtra("Task") as Task?
        System.out.println("Feeding ACTIVITY : "+task);
        setContent {
            B1AndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Feeding(task)
                }
            }
        }
    }
}

@Composable
fun Feeding(initialTask: Task?) {
    if (initialTask == null) {
        System.err.println("Feeding method shoudl be given a Bear")
    }
    val rnds = (300..499).random()
    val rnds2 = (300..499).random()

    Column(modifier = Modifier.padding(16.dp), Arrangement.spacedBy(16.dp)) {
        Text(text = "Feed the bear", style = MaterialTheme.typography.h1)
        Image(
            painter = rememberAsyncImagePainter("https://placekitten.com/"+rnds+"/"+rnds2),
            contentDescription = "Bear food",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clickable(
                    enabled = true,
                    onClickLabel = "Feed the bear",
                    onClick = {
                       // feed the bear with the cat
                    }
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FeedingPreview() {
    B1AndroidTheme {
        // useless
    }
}