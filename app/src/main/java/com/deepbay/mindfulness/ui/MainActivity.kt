package com.deepbay.mindfulness.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.deepbay.mindfulness.R
import com.deepbay.mindfulness.databinding.ActivityMainBinding
import com.deepbay.mindfulness.util.ScreenUtils
import com.deepbay.mindfulness.util.setTransparent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparent(this)
//        val drawable = GradientDrawable(
//            GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(
//                Color.parseColor("#E9DFDD"),
//                Color.parseColor("#C18C84")
//            )
//        )
//        window.setBackgroundDrawable(drawable)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(
                this,
                R.layout.activity_main
            )

        if (ScreenUtils.isPortrait(this)) {
            ScreenUtils.adaptScreen4VerticalSlide(this, 750)
        } else {
            ScreenUtils.adaptScreen4HorizontalSlide(this, 750)
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

    }
}
