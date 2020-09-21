package com.androidevs.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.InterstitialAd
import com.facebook.ads.InterstitialAdListener
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.properties.Delegates

class ActivityA : AppCompatActivity() {
    lateinit var priority:String
    var impressions by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
        val dbref=FirebaseDatabase.getInstance().reference
        



        val btnf=findViewById<Button>(R.id.btn_A)
        val btns=findViewById<Button>(R.id.btn_B)

        val interstalad=com.google.android.gms.ads.InterstitialAd(this)
        interstalad.adUnitId="ca-app-pub-3940256099942544/1033173712"

        val interstitialadfb =
            InterstitialAd(applicationContext, "IMG_16_9_LINK#659258751631857_659263848298014")


        btnf.setOnClickListener {
            dbref.child("AdData").addListenerForSingleValueEvent(
                object :ValueEventListener{
                    override fun onCancelled(error: DatabaseError) {

                    }

                    override fun onDataChange(snapshot: DataSnapshot) {

                        if(snapshot.exists()){
                            val pr=snapshot.child("Priorty").value
                            priority=pr.toString()
                            impressions=snapshot.child("Impressions").value.toString().toInt()

                            if(priority=="admob") {
                                interstalad.loadAd(AdRequest.Builder().build())

                                if(interstalad.isLoaded){
                                    interstalad.show()
                                }
                                else{
                                    Log.d("MAD", "onCreate: Eror while Loading add")
                                }

                                interstalad.adListener= object :AdListener() {
                                    override fun onAdClosed() {
                                        super.onAdClosed()
                                        impressions += 1;
                                        dbref.child("AdData").child("Impressions").setValue(impressions)
                                    }

                                }


                            }




                            if(priority == "Facebook") {



                                val adListener = object : InterstitialAdListener {
                                    override fun onInterstitialDisplayed(p0: Ad?) {

                                    }

                                    override fun onAdClicked(p0: Ad?) {

                                    }

                                    override fun onInterstitialDismissed(p0: Ad?) {
                                        impressions += 1;
                                        dbref.child("AdData").child("Impressions").setValue(impressions)

                                    }

                                    override fun onError(p0: Ad?, p1: AdError?) {
                                        Log.d("Error", " An error occured")

                                    }

                                    override fun onAdLoaded(p0: Ad?) {
                                        Log.d("AD_LOADED", "onAdLoaded: ")
                                        interstitialadfb.show()

                                    }

                                    override fun onLoggingImpression(p0: Ad?) {
//                                        impressions += 1;
//                                        dbref.child("AdData").child("Impressions").setValue(impressions)

                                    }

                                }


                                interstitialadfb.loadAd(
                                    interstitialadfb.buildLoadAdConfig().withAdListener(adListener).build()
                                )
                            }

                            Log.d("Priorty",pr.toString())
                        }

                    }

                }
            )
            

        }
        btns.setOnClickListener {
            dbref.child("AdData").addListenerForSingleValueEvent(
                object :ValueEventListener{
                    override fun onCancelled(error: DatabaseError) {

                    }

                    override fun onDataChange(snapshot: DataSnapshot) {

                        if(snapshot.exists()){
                            val pr=snapshot.child("Priorty").value
                            priority=pr.toString()

                            if(priority=="admob") {
                                interstalad.loadAd(AdRequest.Builder().build())

                                if(interstalad.isLoaded){
                                    interstalad.show()
                                }
                                else{
                                    Log.d("MAD", "onCreate: Eror while Loading add")
                                }

                            }
                            if(priority == "Facebook") {



                                val adListener = object : InterstitialAdListener {
                                    override fun onInterstitialDisplayed(p0: Ad?) {

                                    }

                                    override fun onAdClicked(p0: Ad?) {

                                    }

                                    override fun onInterstitialDismissed(p0: Ad?) {
                                        impressions += 1;
                                        dbref.child("AdData").child("Impressions").setValue(impressions)

                                    }

                                    override fun onError(p0: Ad?, p1: AdError?) {
                                        Log.d("Error", " An error occured")

                                    }

                                    override fun onAdLoaded(p0: Ad?) {
                                        Log.d("AD_LOADED", "onAdLoaded: ")
                                        interstitialadfb.show()

                                    }

                                    override fun onLoggingImpression(p0: Ad?) {
//                                        impressions += 1;
//                                        dbref.child("AdData").child("Impressions").setValue(impressions)

                                    }

                                }


                                interstitialadfb.loadAd(
                                    interstitialadfb.buildLoadAdConfig().withAdListener(adListener).build()
                                )
                            }

                            Log.d("Priorty",pr.toString())
                        }

                    }

                }
            )

        }


    }
}