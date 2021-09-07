package com.example.drcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //create a handler for displaying welcome screen
        Handler().postDelayed(
                {
                    progressBar.visibility= View.INVISIBLE
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()  //back button pe click krne pe ham welcome screen per dubara nhi aayenge
                }, 1000)
    }
}