package com.example.pictureupload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private val RESULT_CODE = 100
    private var imageView:ImageView
        get() {
            return imageView
        }
        set(value) {
            imageView = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageView = findViewById(R.id.imageView)

    }
}