package com.example.taipeitravelattractions

import android.content.Intent
import android.content.res.Configuration
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.taipeitravelattractions.model.Attraction
import com.example.taipeitravelattractions.model.MyImage
import com.example.taipeitravelattractions.ui.attraction.MyImageViewPagerAdapter

class AttractionActivity : AppCompatActivity() {
    private lateinit var adapter: MyImageViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attraction)

        val attraction = intent.parcelable<Attraction>("attraction")
//        Log.d("AttractionActivity", "${attraction?.name}")
        attraction?.let { bind(it) }
    }

    /**
     * [referece](https://stackoverflow.com/a/73311814)
     */
    private inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
        SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }

    private fun bind(attraction: Attraction) {
        val viewPager: ViewPager2 = findViewById(R.id.viewPager_detail_attractionImages)
        val name: TextView = findViewById(R.id.textView_detail_attractionName)
        val introduction: TextView = findViewById(R.id.textView_detail_attractionIntroduction)
        val categories: TextView = findViewById(R.id.textView_detail_attractionCategories)
        val district: TextView = findViewById(R.id.textView_detail_attractionDistrict)
        val address: TextView = findViewById(R.id.textView_detail_attractionAddress)
        val telephone: TextView = findViewById(R.id.textView_detail_attractionTelephone)
        val email: TextView = findViewById(R.id.textView_detail_attractionEmail)
        val map: TextView = findViewById(R.id.textView_detail_attractionMap)
        val official: TextView = findViewById(R.id.textView_detail_attractionWebsite)
        val remind: TextView = findViewById(R.id.textView_detail_attractionRemind)
        val url: TextView = findViewById(R.id.textView_detail_attractionUrl)
        val stringBuilder = StringBuilder()

        // handle non-image attraction
        adapter = if (attraction.images.isEmpty())
            MyImageViewPagerAdapter(listOf(MyImage("")))
        else
            MyImageViewPagerAdapter(attraction.images)
        viewPager.adapter = adapter
        viewPager.currentItem = 0

        name.text = attraction.name
        introduction.text = attraction.introduction

        categories.text = stringBuilder.append("主題分類：")
            .append(attraction.categories.joinToString(",") { category -> category.name })
        stringBuilder.clear()

        district.text = stringBuilder.append("地區：").append(attraction.district)
        stringBuilder.clear()

        address.text = stringBuilder.append("地址：").append(attraction.address.ifBlank { "無" })
        stringBuilder.clear()

        telephone.text = stringBuilder.append("電話：").append(attraction.tel.ifBlank { "無" })
        stringBuilder.clear()

        email.text = stringBuilder.append("電子郵箱：").append(attraction.email.ifBlank { "無" })
        stringBuilder.clear()

        // TODO: hyperlink or intent to Google Map

        official.text = stringBuilder.append("官網：").append(attraction.website.ifBlank { "無" })
        stringBuilder.clear()

        remind.text = stringBuilder.append("提醒：").append(
            if (attraction.remind.isBlank()) "無" else "\n${attraction.remind}"
        )
        stringBuilder.clear()

        // TODO: hyperlink to 臺北市政府觀光傳播局網站

    }

    private fun isDarkModeOn(): Boolean {
        val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES
    }
}