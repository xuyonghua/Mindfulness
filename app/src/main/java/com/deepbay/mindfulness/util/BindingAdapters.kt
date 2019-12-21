/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.deepbay.mindfulness.util

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.deepbay.mindfulness.R
import com.deepbay.mindfulness.domain.Album
import com.deepbay.mindfulness.network.ApiStatus
import java.text.SimpleDateFormat
import java.util.*
import android.graphics.drawable.GradientDrawable as GradientDrawable1

@BindingAdapter("albumImage")
fun ImageView.setAlbumImage(item: Album) {
    item?.let {
        Glide.with(context)
            .load(item.albumImage)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(this)
    }
}

@BindingAdapter("layoutParams")
fun CardView.setLayoutParams(scala: Float) {
    this.layoutParams.width =
        (getDisplayMetrics(context!!).widthPixels.times(scala)).toInt()
}


@BindingAdapter("apiStatus")
fun bindStatus(
    statusImageView: ImageView,
    status: ApiStatus?
) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

//@BindingAdapter("day")
//fun TextView.setDay() {
//    val calendar = Calendar.getInstance()
//    text = calendar.get(Calendar.DAY_OF_MONTH).toString()
//}
//
//@BindingAdapter("year")
//fun TextView.setYear() {
//    val calendar = Calendar.getInstance()
//    text = calendar.get(Calendar.YEAR).toString()
//}
//
//@BindingAdapter("month")
//fun TextView.setMonth() {
//    text = SimpleDateFormat("MMM", Locale.getDefault()).format(Date())
//}

fun getDisplayMetrics(context: Context): DisplayMetrics {
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return metrics
}