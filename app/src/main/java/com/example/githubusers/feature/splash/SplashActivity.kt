package com.example.githubusers.feature.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.example.githubusers.R
import com.example.githubusers.core.base.BaseActivity
import com.example.githubusers.feature.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadAnimation()
        startIntent()
    }

    private fun loadAnimation() {
        val logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation)
        val textAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top_animation)

        imageView.startAnimation(logoAnimation)
        txt_title.startAnimation(textAnimation)
        txt_sub.startAnimation(textAnimation)
    }

    private fun startIntent() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        },2000)
    }
}