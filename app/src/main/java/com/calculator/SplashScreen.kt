package com.calculator


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import gr.net.maroulis.library.EasySplashScreen


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val easySplashScreenView: View = EasySplashScreen(this)
            .withFullScreen()
            .withTargetActivity(MainActivity::class.java)
            .withSplashTimeOut(3500)
            .withBackgroundResource(R.color.blue)
            .withBeforeLogoText("calculate no matter what")
            .withLogo(R.drawable.icon)
            .withAfterLogoText("Powered by M.A.S")
            .create()
        setContentView(easySplashScreenView)
    }
}