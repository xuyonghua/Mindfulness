package com.deepbay.mindfulness.ui

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.deepbay.mindfulness.R
import com.deepbay.mindfulness.databinding.ActivityMainBinding
import com.deepbay.mindfulness.util.adaptHeight
import com.deepbay.mindfulness.util.adaptWidth
import com.deepbay.mindfulness.util.isPortrait
import com.deepbay.mindfulness.util.setTransparent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparent(this)
        val drawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(
                Color.parseColor("#E9DFDD"),
                Color.parseColor("#C18C84")
            )
        )
        window.setBackgroundDrawable(drawable)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(
                this,
                R.layout.activity_main
            )
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
//        Toast.makeText(this, "show toast", Toast.LENGTH_LONG).show()
    }

    override fun getResources(): Resources = if (isPortrait()) {
        adaptWidth(super.getResources(), 375)
    } else {
        adaptHeight(super.getResources(), 667)
    }

    fun setBackground(startColor: String, endColor: String) {
        val drawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(
                Color.parseColor(startColor),
                Color.parseColor(endColor)
            )
        )
        window.setBackgroundDrawable(drawable)
    }
}
