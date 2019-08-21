package com.wynnsolutionsmyanmar.aesnoteencrypt.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.wynnsolutionsmyanmar.aesnoteencrypt.R

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onResume() {
        super.onResume()

        Handler().postDelayed(Runnable {
            // finish the splash activity so it can't be returned to
            this@SplashScreen.finish()
            // create an Intent that will start the second activity
            val mainIntent = Intent(this@SplashScreen, SetPinCode::class.java)
            this@SplashScreen.startActivity(mainIntent)
        }, 3000) // 3000 milliseconds
    }
}