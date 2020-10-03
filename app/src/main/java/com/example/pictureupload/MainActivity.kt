package com.example.pictureupload

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileDescriptor

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.setType("image/*")
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null){
            // 選択した画像のUriからbitmap取得
            val bitmap = getBitmapFormUri(data.data)
            imageView.setImageBitmap(bitmap)
        }
    }

    // Bitmapを取得するメソッド
    private fun getBitmapFormUri(uri: Uri?): Bitmap?{
        uri?: return null

        val parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r")
        parcelFileDescriptor?: return null

        val image = BitmapFactory.decodeFileDescriptor(parcelFileDescriptor.fileDescriptor)
        parcelFileDescriptor.close()

        return image
    }
}