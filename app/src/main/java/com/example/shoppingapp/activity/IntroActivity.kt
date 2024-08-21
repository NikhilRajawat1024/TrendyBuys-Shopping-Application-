package com.example.shoppingapp.activity

import android.content.Intent
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {

    private lateinit var binding: ActivityIntroBinding

    private lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startbtn.setOnClickListener{
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
        }

        lottieAnimationView = findViewById(R.id.lottieanimatinoview)

        // Start playing the animation
        lottieAnimationView.playAnimation()

        setStatusBarColor(R.color.white)
    }

    private fun setStatusBarColor(colorResId: Int) {
        window.statusBarColor = resources.getColor(colorResId, theme)
    }
}
