package com.androidevs.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

class FragmentA : Fragment() {

    companion object{
        val numberofads:Int=4
    }
    lateinit var list: List<DataClass>
    lateinit var recylerviewitems:ArrayList<Any>
    lateinit var adloader:AdLoader
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v= inflater.inflate(R.layout.fragment_a,container,false)
        recylerviewitems=ArrayList()

        recyclerView=v.findViewById(R.id.recylerview1)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        getData()
        getBannerAds()
        loadAd()

        val adapter=MyAdapter(requireContext(),recylerviewitems)
        recyclerView.adapter=adapter





        return v
    }
    private  fun getData(){
          recylerviewitems.add(DataClass("https://s1.1zoom.me/b4361/17/Sunrises_and_sunsets_Grasslands_Scenery_Grass_Hill_589506_240x320.jpg"
          ,"Ali",20))
        recylerviewitems.add(DataClass( "https://s1.1zoom.me/b6069/560/Amanita_Mushrooms_nature_Closeup_Moss_590084_240x320.jpg"
            ,"james",8))
        recylerviewitems.add(DataClass("https://s1.1zoom.me/b6258/241/Mountains_Roads_Bicycle_Motion_589012_240x320.jpg"
            ,"Gordon",43))
        recylerviewitems.add(DataClass("https://s1.1zoom.me/b4767/386/Fields_Sunflowers_Summer_Bokeh_588398_240x320.jpg"
            ,"Tom Cruise",50))
        recylerviewitems.add(DataClass("https://s1.1zoom.me/b5454/365/Mushrooms_nature_Penny_bun_Moss_588281_240x320.jpg"
            ,"SRK",55))
        recylerviewitems.add(DataClass( "https://s1.1zoom.me/b5060/304/Mountains_Trail_Tourist_Back_view_Backpack_Fog_582726_240x320.jpg","Robert Downey JR."
            ,49))
        recylerviewitems.add(DataClass( "https://s1.1zoom.me/b5446/797/England_Peak_district_Buxton_Grinlow_Tower_Tower_586352_240x320.jpg"
            ,"Mark Whalberg",40))
        recylerviewitems.add(DataClass( "https://s1.1zoom.me/b5149/147/Sky_Night_Clouds_Moon_582178_240x320.jpg"
            ,"Brad Pitt",46))
        recylerviewitems.add(DataClass("https://s1.1zoom.me/b5151/169/Lavandula_Fields_Kristina_Makeeva_Balls_Night_582665_240x320.jpg"
            ,"Matt Damon",53))
        recylerviewitems.add(DataClass("https://s1.1zoom.me/b5446/797/England_Peak_district_Buxton_Grinlow_Tower_Tower_586352_240x320.jpg"
            ,"Christ Evans",51))
        recylerviewitems.add(DataClass("https://s1.1zoom.me/b5454/365/Mushrooms_nature_Penny_bun_Moss_588281_240x320.jpg"
            ,"Tom Holland",25))
        recylerviewitems.add(DataClass("https://s1.1zoom.me/b6258/241/Mountains_Roads_Bicycle_Motion_589012_240x320.jpg"
            ,"Will Smith",48))
        recylerviewitems.add(DataClass("https://s1.1zoom.me/b4361/17/Sunrises_and_sunsets_Grasslands_Scenery_Grass_Hill_589506_240x320.jpg"
            ,"Christian Bale",59))



    }

    private fun getBannerAds(){
        run {
            var i = 0
            while (i < recylerviewitems.size) {
                val adview = AdView(requireContext())
                adview.adSize= AdSize.BANNER
                adview.adUnitId="ca-app-pub-3940256099942544/6300978111"

                recylerviewitems.add(i,adview)

                i += numberofads

            }
        }


    }

    private fun loadAd(){
        var i=0
        while (i<recylerviewitems.size){

            val obj= recylerviewitems[i]

            if(obj is AdView){
                val adView=obj as AdView
                adView.loadAd(AdRequest.Builder().build())
            }
            i++
        }
    }



}