package com.example.taipeitravelattractions.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taipeitravelattractions.AttractionActivity
import com.example.taipeitravelattractions.R
import com.example.taipeitravelattractions.model.Attraction
import com.example.taipeitravelattractions.ui.AnimationCreator

/**
 * [referece](https://github.com/android/codelab-android-paging/blob/main/advanced/end/app/src/main/java/com/example/android/codelabs/paging/ui/RepoViewHolder.kt)
 */
class AttractionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.imageView_attraction)
    private val name: TextView = view.findViewById(R.id.textView_attractionName)
    private val district: TextView = view.findViewById(R.id.textView_attractionDistrict)
    private val category: TextView = view.findViewById(R.id.textView_attractionCategory)

    private var attraction: Attraction? = null

    init {
        view.setOnClickListener { _ ->
            attraction?.let {
                val intent = Intent(view.context, AttractionActivity::class.java)
                intent.putExtra("attraction", it)
                view.context.startActivity(intent)
            }
        }
    }

    fun bind(attraction: Attraction?) {
        if (attraction == null) {
            val resources = itemView.resources
            image.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources, R.drawable.baseline_image_not_supported_24, null
                )
            )
            name.text = resources.getString(R.string.loading)
            district.visibility = View.GONE
            category.visibility = View.GONE
        } else {
            showAttractionData(attraction)
        }
    }

    private fun showAttractionData(attraction: Attraction) {
        this.attraction = attraction
        val resources = itemView.resources

        if (attraction.images.isEmpty())
            image.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources, R.drawable.baseline_image_not_supported_24, null
                )
            )
        else
            Glide.with(itemView).load(attraction.images[0].src)
                .placeholder(AnimationCreator.createCircularProgressDrawable(itemView.context))
                .into(image)
        name.text = attraction.name
        district.text = attraction.district
        district.visibility = View.VISIBLE
        if (attraction.categories.isEmpty()) {
            category.visibility = View.GONE
        } else {
            category.text = attraction.categories[0].name
            category.visibility = View.VISIBLE
        }
    }

    companion object {
        /**
         * move the instantiate code here, so the logic is managed in this method instead of other place
         *
         * see [companion object](https://kotlinlang.org/docs/object-declarations.html#companion-objects)
         */
        fun create(parent: ViewGroup): AttractionViewHolder {
            return AttractionViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_attraction, parent, false)
            )
        }
    }
}