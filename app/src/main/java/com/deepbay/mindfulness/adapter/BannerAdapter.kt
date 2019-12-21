package com.deepbay.mindfulness.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deepbay.mindfulness.databinding.ViewBannerItemViewBinding
import com.deepbay.mindfulness.domain.Album

class BannerAdapter(private val clickListener: BannerListener) :
    RecyclerView.Adapter<BannerAdapter.ViewHolder>() {
    var data = listOf<Album>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(private val binding: ViewBannerItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Album, clickListener: BannerListener) {
            binding.album = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewBannerItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class BannerListener(val clickListener: (albumId: Long) -> Unit) {
    fun onClick(album: Album) = clickListener(album.id)
}