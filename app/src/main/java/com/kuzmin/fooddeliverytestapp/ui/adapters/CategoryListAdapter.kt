package com.kuzmin.fooddeliverytestapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kuzmin.fooddeliverytestapp.databinding.ItemRvCategoryBinding
import com.kuzmin.fooddeliverytestapp.domain.model.food.CategoryItem

class CategoryListAdapter
    : ListAdapter<CategoryItem, CategoryListAdapter.CategoryViewHolder>(CategoryDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemRvCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)

        with(holder) {
            with(category) {
                binding.tvCategory.text = title
                binding.ivCategory.setImageResource(picture)

                binding.root.setOnClickListener {
                    Log.d("TAG", "Category: $title")
                }
            }
        }
    }

    inner class CategoryViewHolder(
        val binding: ItemRvCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object CategoryDiffCallback : DiffUtil.ItemCallback<CategoryItem>() {

        override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
            return oldItem.title == newItem.title
        }
    }
}