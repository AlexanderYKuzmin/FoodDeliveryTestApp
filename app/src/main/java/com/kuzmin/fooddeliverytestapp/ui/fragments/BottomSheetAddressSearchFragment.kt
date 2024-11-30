package com.kuzmin.fooddeliverytestapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kuzmin.fooddeliverytestapp.databinding.BottomSheetFragmentBinding
import com.kuzmin.fooddeliverytestapp.domain.model.AddressSuggestionResult
import com.kuzmin.fooddeliverytestapp.domain.model.address.Address
import com.kuzmin.fooddeliverytestapp.extension.showToast
import com.kuzmin.fooddeliverytestapp.ui.adapters.AddressListAdapter
import com.kuzmin.fooddeliverytestapp.ui.viewmodels.BottomSheetViewModel
import com.kuzmin.fooddeliverytestapp.util.ErrorHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetAddressSearchFragment  : BottomSheetDialogFragment() {

    private var _binding: BottomSheetFragmentBinding? = null
    private val binding get() = _binding!!

    private val bottomSheetViewModel: BottomSheetViewModel by viewModels()

    private val addressAdapter: AddressListAdapter by lazy { AddressListAdapter(::addressClickListener) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchQuery()

        setCurrentPositionListener()

        bottomSheetViewModel.addressResult.observe(viewLifecycleOwner) {
            when (it) {
                is AddressSuggestionResult.AddressDataSuccess -> {
                    binding.rvBottomSheet.adapter = addressAdapter
                    addressAdapter.submitList(it.addressList)
                }
                is AddressSuggestionResult.AddressByLocationSuccess -> {
                    binding.svAddressSuggestion.setQuery(
                        it.addressList[0].address, false
                    )
                }
                is AddressSuggestionResult.Error -> {
                    requireActivity().showToast(
                        ErrorHandler.handleError(it.throwable, requireContext().resources)
                    )
                    Log.d("ERROR", "Error: ${it.throwable.message}")
                }
            }
        }
    }


    private fun setupSearchQuery() {
        binding.svAddressSuggestion.apply {
            isIconified = false
            setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        bottomSheetViewModel.updateQuery(newText)
                    }
                    return true
                }
            })
        }
    }

    private fun setCurrentPositionListener() {
        binding.llCurrentPosition.setOnClickListener {
            bottomSheetViewModel.getAddressByLocation()
        }
    }

    private fun addressClickListener(address: Address) {
        bottomSheetViewModel.writeAddressToDatastore(address)
        dismiss()
    }

    companion object {
        const val TAG = "BottomSheetSearchAddressFragmentTag"
    }
}