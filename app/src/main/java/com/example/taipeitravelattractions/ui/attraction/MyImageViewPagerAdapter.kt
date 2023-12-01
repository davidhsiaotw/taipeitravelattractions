package com.example.taipeitravelattractions.ui.attraction

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taipeitravelattractions.model.MyImage

class MyImageViewPagerAdapter(private val imageList: List<MyImage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewPagerViewHolder.create(parent)
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewPagerViewHolder).bind(imageList[position])
    }

}