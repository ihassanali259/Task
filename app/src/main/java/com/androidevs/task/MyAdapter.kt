package com.androidevs.task

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdView
import com.squareup.picasso.Picasso
import java.lang.Exception

class MyAdapter(context: Context,mydatalist:ArrayList<Any>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var arraylist=ArrayList<Any>()
    lateinit var context: Context
    lateinit var imgview:ImageView
    lateinit var textviewname:TextView
    lateinit var textviewage:TextView
    init {
        this.arraylist=mydatalist
        this@MyAdapter.context =context
    }


    companion object{
        val ITEM_DATA=0
        val ITEM_AD=1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when(viewType){
            ITEM_DATA->{
                val view=LayoutInflater.from(parent.context).inflate(R.layout.itemlist,parent,false)
                return ViewHolderData(view)
            }
            ITEM_AD-> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.banner_ad_container, parent, false)
                return BannerADclass(view)
            }
            else->{
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.banner_ad_container, parent, false)
                return BannerADclass(view)
            }


        }

    }

    override fun getItemCount(): Int {
        return arraylist.size

    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val viewtype=getItemViewType(position)
        when(viewtype){
            ITEM_DATA->{
                 var btmap:Bitmap?
                val dataholder=holder as ViewHolderData
                val dataclass=arraylist[position] as DataClass

                Picasso.get().load(dataclass.imgURL).into(object:com.squareup.picasso.Target{
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

                    }

                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                      holder.imggview.setImageBitmap(bitmap)
                        btmap=bitmap
                    }

                })
                holder.textage.text=dataclass.age.toString()
                holder.textname.text=dataclass.name

                holder.crdview.setOnClickListener {
                    val intent=Intent("SEND_DATA")
                    intent.putExtra("name",dataclass.name)
                    intent.putExtra("age",dataclass.age.toString())
                    intent.putExtra("url",dataclass.imgURL)
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
                }




            }

            ITEM_AD->{
                val bannerview=holder as BannerADclass
                val adview=arraylist[position] as AdView
                val viewgroup=bannerview.itemView as ViewGroup
                if(viewgroup.childCount>0){
                    viewgroup.removeAllViews()
                }

                if(viewgroup.parent!=null){
                    val adview1=adview.parent as ViewGroup
                    adview1.removeView(adview)
                }

                viewgroup.addView(adview)

            }

            else->{
                val bannerview=holder as BannerADclass
                val adview=arraylist[position] as AdView
                val viewgroup=bannerview.itemView as ViewGroup
                if(viewgroup.childCount>0){
                    viewgroup.removeAllViews()
                }

                if(viewgroup.parent!=null){
                    val adview1=adview.parent as ViewGroup
                    adview1.removeView(adview)
                }

                viewgroup.addView(adview)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        if(position%FragmentA.numberofads==0)
            return ITEM_AD
        else
            return ITEM_DATA
    }

    private inner class ViewHolderData(itemview:View): RecyclerView.ViewHolder(
        itemview
    ) {
           val imggview=itemview.findViewById<ImageView>(R.id.imgview)
        val textname=itemview.findViewById<TextView>(R.id.textviewname)
        val textage=itemview.findViewById<TextView>(R.id.textviewage)
        val crdview=itemview.findViewById<CardView>(R.id.cardviewlist)


    }
    private inner class BannerADclass(itemview: View):RecyclerView.ViewHolder(itemview){


    }

}