package com.ttd.dasasahitya.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ttd.dasasahitya.R


class ImageViewAdapter(private val viewPager2: ViewPager2) : RecyclerView.Adapter<ImageViewAdapter.ViewImageHolder>() {
//    private val colorArray = arrayListOf(Color.DKGRAY, Color.CYAN, Color.GREEN, Color.GRAY)
    private val imageArray = arrayListOf(R.drawable.pager_1, R.drawable.pager_2)

    inner class ViewImageHolder(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewImageHolder {
        return ViewImageHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_slider, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewImageHolder, position: Int) {
        holder.itemView.findViewById<ImageView>(R.id.image_view).setImageResource(imageArray[position])
        if(position==imageArray.size-1){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return imageArray.size
    }

    private val runnable = kotlinx.coroutines.Runnable {
        imageArray.addAll(imageArray)
        notifyDataSetChanged()
    }


}