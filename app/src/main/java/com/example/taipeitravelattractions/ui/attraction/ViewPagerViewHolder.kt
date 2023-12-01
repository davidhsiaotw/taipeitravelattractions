package com.example.taipeitravelattractions.ui.attraction

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taipeitravelattractions.R
import com.example.taipeitravelattractions.model.MyImage
import com.example.taipeitravelattractions.ui.AnimationCreator

class ViewPagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imageView: ImageView = view.findViewById(R.id.imageView_attraction_4viewpager)

    fun bind(image: MyImage) {
        if (image.imageType == "none")
            imageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    itemView.resources, R.drawable.baseline_image_not_supported_24, null
                )
            )
        else
        // loading progress animation: https://stackoverflow.com/a/35306315/22598753
            Glide.with(itemView).load(image.src)
                .placeholder(AnimationCreator.createCircularProgressDrawable(itemView.context))
                .into(imageView)
    }

    companion object {
        /**
         * move the instantiate code here, so the logic is managed in this method instead of other place
         *
         * see [companion object](https://kotlinlang.org/docs/object-declarations.html#companion-objects)
         */
        fun create(parent: ViewGroup): ViewPagerViewHolder {
            return ViewPagerViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_attraction_image, parent, false)
            )
        }
    }
}