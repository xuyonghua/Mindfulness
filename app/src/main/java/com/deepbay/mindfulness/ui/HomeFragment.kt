package com.deepbay.mindfulness.ui

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deepbay.mindfulness.R
import com.deepbay.mindfulness.databinding.HomeFragmentBinding
import com.deepbay.mindfulness.widget.bannerview.BannerSetting
import com.deepbay.mindfulness.widget.bannerview.ScaleBannerLayoutManager

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    private val data = listOf(
        "https://res-mindfullness-vigour-wechat.oss-cn-shenzhen.aliyuncs.com/images/album/creativity%403x.png",
        "https://res-mindfullness-vigour-wechat.oss-cn-shenzhen.aliyuncs.com/images/album/stress%403x.png",
        "https://res-mindfullness-vigour-wechat.oss-cn-shenzhen.aliyuncs.com/images/album/anxiety%403x.png",
        "https://res-mindfullness-vigour-wechat.oss-cn-shenzhen.aliyuncs.com/images/album/creativity%403x.png",
        "https://res-mindfullness-vigour-wechat.oss-cn-shenzhen.aliyuncs.com/images/album/focused%403x.png",
        "https://res-mindfullness-vigour-wechat.oss-cn-shenzhen.aliyuncs.com/images/album/sleep%403x.png"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
//        binding.banner.layoutManager = BannerLayoutManager()

//        val adapter = BannerAdapter()
//        adapter.data = data
//        binding.banner.setUp(BannerSetting().apply {
//            slideTimeGap = 3000 // 自动滑动的时间间隔
//            autoSlideSpeed = 1000 // 自动滑动一次的速度
//            loop = true // 是否循环滑动
//            canAutoSlide = true // 是否自动滑动
//        }, adapter)

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
        binding.banner.layoutManager = ScaleBannerLayoutManager()
        binding.banner.setUp(BannerSetting().apply {
            slideTimeGap = 3000
            autoSlideSpeed = 1000
            loop = true
            canAutoSlide = true
        }, Adapter())



        return binding.root
    }

    fun getDisplayMetrics(context: Context): DisplayMetrics {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        return metrics
    }

    inner class Adapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(group: ViewGroup, position: Int): ViewHolder {
            val item = LayoutInflater.from(activity).inflate(R.layout.view_banner_item_view, group, false)
            return ViewHolder(item)
        }

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.cardItem.layoutParams?.width = (getDisplayMetrics(activity!!).widthPixels.times(0.85f)).toInt()
            Glide.with(activity!!).load(data[position]).into(holder.image)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.banner_image)
        val cardItem: CardView = itemView.findViewById(R.id.card_item)
    }
}
