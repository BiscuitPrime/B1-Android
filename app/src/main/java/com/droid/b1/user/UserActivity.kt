package com.droid.b1.user

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.droid.b1.R

class UserActivity : AppCompatActivity() {

    private var pictureButton : Button?=null;
    private var bitmap : Bitmap?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        pictureButton = findViewById(R.id.takePictureButton);

        pictureButton?.setOnClickListener {
            takePicture.launch(intent);
        }

    }

    val takePicture = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        bitmap = it
    }

}