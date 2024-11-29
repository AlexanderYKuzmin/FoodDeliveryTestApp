package com.kuzmin.fooddeliverytestapp.ui.adapters

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kuzmin.fooddeliverytestapp.databinding.ItemRvDiscountBinding
import com.kuzmin.fooddeliverytestapp.domain.model.food.DiscountItem

class DiscountListAdapter : ListAdapter<DiscountItem, DiscountListAdapter.DiscountViewHolder>(DiscountDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountViewHolder {
        val binding = ItemRvDiscountBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DiscountViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DiscountViewHolder, position: Int) {
        val discount = getItem(position)
        with(holder.binding) {
            with(discount) {
                ivDiscout.setImageResource(picture)
                tvDescription.text = title
                tvWeight.text = String.format("%d г", weight)
                tvDiscountOldPrice.text = String.format("%d \u20BD", oldPrice)
                tvDiscountOldPrice.paintFlags = tvDiscountOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                tvDiscountNewPrice.text = String.format("%d \u20BD", newPrice)
                tvDiscountValue.text = String.format("-%d%%", discountValue)
                tvDiscountIsNew.visibility = if (isNew == 1) {
                    View.VISIBLE
                } else {
                    View.GONE
                }

                root.setOnClickListener {
                    Log.d("TAG", "Discount: $title")
                }

                ivAddGoodPlus.setOnClickListener {
                    handleAddGoodIncrease(holder, discount)
                }

                ivAddGoodMinus.setOnClickListener {
                    handleAddGoodDecrease(holder, discount)
                }
            }
        }
    }

    private fun handleAddGoodIncrease(holder: DiscountViewHolder, discount: DiscountItem) {
        with(holder.binding) {
            if (discount.amount == 0) {
                llPrice.visibility = View.GONE
                tvAmountAddGood.visibility = View.VISIBLE
                ivAddGoodMinus.visibility = View.VISIBLE

                tvDiscountOldPrice.visibility = View.GONE
                tvDiscountNewPrice.visibility = View.GONE
            }
            discount.increaseAmount()
            tvAmountAddGood.text = discount.amount.toString()
        }
    }

    private fun handleAddGoodDecrease(holder: DiscountViewHolder, discount: DiscountItem) {
        with(holder.binding) {
            if (discount.amount == 1) {
                llPrice.visibility = View.VISIBLE
                tvAmountAddGood.visibility = View.GONE
                ivAddGoodMinus.visibility = View.GONE

                tvDiscountOldPrice.visibility = View.VISIBLE
                tvDiscountNewPrice.visibility = View.VISIBLE
            }
            discount.decreaseAmount()
            tvAmountAddGood.text = discount.amount.toString()
        }
    }

    inner class DiscountViewHolder(
        val binding: ItemRvDiscountBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object DiscountDiffCallback : DiffUtil.ItemCallback<DiscountItem>() {
        override fun areItemsTheSame(oldItem: DiscountItem, newItem: DiscountItem): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: DiscountItem, newItem: DiscountItem): Boolean {
            return oldItem.picture == newItem.picture
        }
    }
}