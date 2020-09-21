package com.androidevs.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_b.*
import org.w3c.dom.Text

class FragmentB : Fragment() {
    val dbref= FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val v=inflater.inflate(R.layout.fragment_b,container,false)

        val imgview=v.findViewById<ImageView>(R.id.imgviewb)
        val textViewname=v.findViewById<TextView>(R.id.textviewname)
        val textViewage=v.findViewById<TextView>(R.id.textviewage)
        val impression=v.findViewById<TextView>(R.id.textvieimpressions)

        Picasso.get().load(arguments?.getString("url")).into(imgview)
        textViewname.text=arguments?.getString("name")
        textViewage.text=arguments?.getString("age")


        dbref.child("AdData").addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
               if(snapshot.exists()){
                   val imp=snapshot.child("Impressions").value.toString()
                   textvieimpressions.text=imp
               }
            }

        })

        return v
    }
}