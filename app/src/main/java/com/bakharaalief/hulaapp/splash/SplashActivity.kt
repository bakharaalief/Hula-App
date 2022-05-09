package com.bakharaalief.hulaapp.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bakharaalief.hulaapp.R
import com.bakharaalief.hulaapp.main.MainActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val splashTime = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //handle delay
        MainScope().launch {
            toMain()
        }
    }

    private suspend fun toMain() {
        delay(splashTime)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}