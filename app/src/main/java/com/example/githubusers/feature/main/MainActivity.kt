package com.example.githubusers.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubusers.R
import com.example.githubusers.core.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}