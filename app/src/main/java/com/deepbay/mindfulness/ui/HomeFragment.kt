package com.deepbay.mindfulness.ui

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deepbay.mindfulness.R
import com.deepbay.mindfulness.adapter.BannerAdapter
import com.deepbay.mindfulness.adapter.BannerListener
import com.deepbay.mindfulness.databinding.HomeFragmentBinding
import com.deepbay.mindfulness.domain.Album
import com.deepbay.mindfulness.widget.bannerview.BannerSetting
import com.deepbay.mindfulness.widget.bannerview.BannerView
import com.deepbay.mindfulness.widget.bannerview.ScaleBannerLayoutManager
import timber.log.Timber


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
    private val albums = listOf(
        Album(
            1,
            "舒缓压力",
            "停留片刻，放松身心。",
            "https://res-mindfullness-vigour-wechat.deepbaysz.com/images/album/anxiety_cover.png",
            "https://res-mindfullness-vigour-wechat.deepbaysz.com/images/album/anxiety%403x.png",
            "#E9DFDD",
            "#C18C84",
            ""
        ),
        Album(
            2,
            "保持专注",
            "任何时刻，进入心流状态。",
            "https://res-mindfullness-vigour-wechat.deepbaysz.com/images/album/focused_cover.png",
            "https://res-mindfullness-vigour-wechat.deepbaysz.com/images/album/focused%403x.png",
            "#CCC8DE",
            "#8188C0",
            ""
        ),
        Album(
            3,
            "睡前放松",
            "放下一天的劳累，安然入眠。",
            "https://res-mindfullness-vigour-wechat.deepbaysz.com/images/album/sleep_cover.png",
            "https://res-mindfullness-vigour-wechat.deepbaysz.com/images/album/sleep%403x.png",
            "#4B6CB7",
            "#182848",
            ""
        ),
        Album(
            4,
            "焦虑管理",
            "缓解担忧，回归平静。",
            "https://res-mindfullness-vigour-wechat.deepbaysz.com/images/album/creativity_cover.png",
            "https://res-mindfullness-vigour-wechat.deepbaysz.com/images/album/creativity%403x.png",
            "#C3D2F0",
            "#6CB5E5",
            ""
        ),
        Album(
            5,
            "激发创造力",
            "在混沌时，激活灵感之“泉”。",
            "https://res-mindfullness-vigour-wechat.deepbaysz.com/images/album/stress_cover.png",
            "https://res-mindfullness-vigour-wechat.deepbaysz.com/images/album/stress%403x.png",
            "#F1D6BA",
            "#E3BF8A",
            ""
        ),
        Album(
            6,
            "张满的正念课",
            "缓解情绪困扰，促进身心健康。",
            "https://res-mindfullness-vigour-wechat.deepbaysz.com/images/album/zhangman_cover.png",
            "https://res-mindfullness-vigour-wechat.deepbaysz.com/images/album/zhangman.png",
            "#ABBF96",
            "#6E968E",
            ""
        )
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        val adapter = BannerAdapter(BannerListener { albumId: Long ->
            viewModel.onAlbumClicked(albumId)
        })

        adapter.data = albums
        viewModel.onAlbumChanged(albums[0])

        binding.banner.pageListener = BannerView.PageChangeListener { position ->
            val album = albums[position]
            (activity as MainActivity).setBackground(album.startColor, album.endColor)
            viewModel.onAlbumChanged(album)
        }

        viewModel.navigateToAlbumDetail.observe(this, Observer { album ->
            album?.let {
                this.findNavController().navigate(R.id.action_homeFragment_to_musicListFragment)
                viewModel.onAlbumDetailNavigated()
            }
        })

        binding.banner.layoutManager = ScaleBannerLayoutManager()

        binding.banner.setUp(BannerSetting().apply {
            slideTimeGap = 3000
            autoSlideSpeed = 1000
            loop = true
            canAutoSlide = false
        }, adapter)

        binding.lifecycleOwner = this
        return binding.root
    }
}
