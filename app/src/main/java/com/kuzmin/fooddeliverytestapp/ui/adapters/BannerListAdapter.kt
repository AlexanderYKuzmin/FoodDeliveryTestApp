package com.kuzmin.fooddeliverytestapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kuzmin.fooddeliverytestapp.databinding.ItemRvBannerBinding
import com.kuzmin.fooddeliverytestapp.domain.model.food.BannerItem

class BannerListAdapter(

) : ListAdapter<BannerItem, BannerListAdapter.BannerViewHolder>(BannerDiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = ItemRvBannerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = getItem(position)
        with(holder.binding) {
            with(banner) {
                ivBanner.setImageResource(picture)

                root.setOnClickListener {
                    Log.d("TAG", "Banner: $picture")
                }
            }
        }
    }

    inner class BannerViewHolder(
        val binding: ItemRvBannerBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object BannerDiffCallback : DiffUtil.ItemCallback<BannerItem>() {
        override fun areItemsTheSame(oldItem: BannerItem, newItem: BannerItem): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: BannerItem, newItem: BannerItem): Boolean {
            return oldItem.picture == newItem.picture
        }
    }
}