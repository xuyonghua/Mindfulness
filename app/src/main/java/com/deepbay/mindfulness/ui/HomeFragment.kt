package com.deepbay.mindfulness.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deepbay.mindfulness.R
import com.deepbay.mindfulness.adapter.BannerAdapter
import com.deepbay.mindfulness.databinding.HomeFragmentBinding
import com.deepbay.mindfulness.widget.bannerview.BannerLayoutManager
import com.deepbay.mindfulness.widget.bannerview.BannerPageSnapHelper
import com.deepbay.mindfulness.widget.bannerview.BannerSetting

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    private val data = listOf(
        "https://www.wanandroid.com/blogimgs/fa822a30-00fc-4e0d-a51a-d704af48205c.jpeg",
        "https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
        "https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.banner.layoutManager = BannerLayoutManager()
        binding.banner.snapHelper = BannerPageSnapHelper()

        val adapter = BannerAdapter()
        adapter.data = data
        binding.banner.setUp(BannerSetting().apply {
            slideTimeGap = 3000 // 自动滑动的时间间隔
            autoSlideSpeed = 1000 // 自动滑动一次的速度
            loop = true // 是否循环滑动
            canAutoSlide = true // 是否自动滑动
        }, adapter)

//        binding.banner.setUp(BannerSetting().apply {
//            slideTimeGap = 3000 // 自动滑动的时间间隔
//            autoSlideSpeed = 1000 // 自动滑动一次的速度
//            loop = true // 是否循环滑动
//            canAutoSlide = true // 是否自动滑动
//        },object: RecyclerView.Adapter<ImageViewHolder>() {
//            override fun onCreateViewHolder(group: ViewGroup, viewType: Int): ImageViewHolder {
//                val item = LayoutInflater.from(activity).inflate(R.layout.view_banner_item_view, group, false)
//                return ImageViewHolder(item)
//            }
//
//            override fun getItemCount() = data.size
//
//            override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
//                Glide.with(activity!!).load(data[position]).into(holder.image)
//            }
//        })

        return binding.root
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.banner_image)
    }
}
