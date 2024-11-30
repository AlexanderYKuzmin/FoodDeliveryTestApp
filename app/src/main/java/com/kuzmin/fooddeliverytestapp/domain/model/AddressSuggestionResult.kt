package com.kuzmin.fooddeliverytestapp.domain.model

import com.kuzmin.fooddeliverytestapp.domain.model.address.Address

sealed class AddressSuggestionResult {
    class AddressByLocationSuccess(val addressList: List<Address>) : AddressSuggestionResult()

    class AddressDataSuccess(val addressList: List<Address>) : AddressSuggestionResult()

    class Error(val throwable: Throwable) : AddressSuggestionResult()
}