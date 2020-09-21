package com.androidevs.task

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class ActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        loadFragment(FragmentA())

        LocalBroadcastManager.getInstance(this).
        registerReceiver(eventReciever, IntentFilter("SEND_DATA"))
    }

    val eventReciever=object:BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
           val name= p1?.extras?.getString("name")
            val age=p1?.extras?.getString("age").toString()
            val url=p1?.extras?.getString("url")
            Log.d("NAME", "onReceive: $name")
            Log.d("Age", "onReceive: $age")
            Log.d("URL", "onReceive: $url")

            val bundle=Bundle()
            bundle.putString("name",name)
            bundle.putString("age",age)
            bundle.putString("url",url)
            val fragmentB=FragmentB()
            fragmentB.arguments=bundle
            loadFragment(fragmentB)
        }

    }

    private  fun loadFragment(fragment:Fragment){
        val manager=supportFragmentManager
        val transaction=manager.beginTransaction()
        transaction.replace(R.id.fragmentcontainer,fragment)
        transaction.commitAllowingStateLoss()
    }
}