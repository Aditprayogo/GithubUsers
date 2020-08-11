package com.example.githubusers.core.util

import android.graphics.Bitmap
import android.os.AsyncTask
import java.io.InputStream
import java.lang.Exception
import java.net.URL

@Suppress("DEPRECATION")
class ImageTask : AsyncTask<String, Void, Bitmap>() {
    override fun doInBackground(vararg params: String?): Bitmap? {
        var bitmap : Bitmap? = null
        try {
            val input : InputStream = URL(params[0]).openStream()
        }catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }
}