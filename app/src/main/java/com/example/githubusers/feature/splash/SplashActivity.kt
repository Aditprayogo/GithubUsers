package com.example.githubusers.feature.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.example.githubusers.R
import com.example.githubusers.core.base.BaseActivity
import com.example.githubusers.databinding.ActivitySplashBinding
import com.example.githubusers.feature.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

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
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        },2000)
    }
}