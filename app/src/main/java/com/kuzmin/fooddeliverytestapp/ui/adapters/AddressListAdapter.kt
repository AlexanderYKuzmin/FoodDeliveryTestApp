package com.kuzmin.fooddeliverytestapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kuzmin.fooddeliverytestapp.databinding.ItemRvAddressBinding
import com.kuzmin.fooddeliverytestapp.domain.model.address.Address

class AddressListAdapter(
    private val onAddressClickListener: (address: Address) -> Unit
) : ListAdapter<Address, AddressListAdapter.AddressViewHolder>(AddressDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val binding = ItemRvAddressBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AddressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val address = getItem(position)

        with(holder) {
            with(address) {
                binding.tvStreet.text = String.format("%s %s., %s", street, streetType, house)
                binding.tvCityRegion.text = String.format("%s, %s, %s", city, regionWithType, country)

                binding.root.setOnClickListener {
                    onAddressClickListener.invoke(this)
                }
            }
        }
    }

    inner class AddressViewHolder(
        val binding: ItemRvAddressBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object AddressDiffCallback : DiffUtil.ItemCallback<Address>() {

        override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean {
            return oldItem.fullAddress == newItem.fullAddress
        }

        override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean {
            return oldItem == newItem
        }
    }
}