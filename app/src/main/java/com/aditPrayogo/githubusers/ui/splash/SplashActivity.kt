package com.aditPrayogo.githubusers.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.aditPrayogo.githubusers.R
import com.aditPrayogo.githubusers.databinding.ActivitySplashBinding
import com.aditPrayogo.githubusers.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val binding : ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadAnimation()
        startIntent()
    }

    private fun loadAnimation() {
        val logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation)
        val textAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top_animation)

        binding.apply {
            imageView.startAnimation(logoAnimation)
            txtTitle.startAnimation(textAnimation)
            txtSub.startAnimation(textAnimation)
        }
    }

    private fun startIntent() {
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java)).also { finish() }
        },2000)
    }
}