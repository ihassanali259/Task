package com.androidevs.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.InterstitialAd
import com.facebook.ads.InterstitialAdListener


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btna=findViewById<Button>(R.id.Button_A)
        val btnb=findViewById<Button>(R.id.Button_B)
       // MediationTestSuite.launch(this)

        btna.setOnClickListener {
            startActivity(Intent(this,ActivityA::class.java))

        }
        btnb.setOnClickListener {
            startActivity(Intent(this,ActivityB::class.java))
        }



    }

}