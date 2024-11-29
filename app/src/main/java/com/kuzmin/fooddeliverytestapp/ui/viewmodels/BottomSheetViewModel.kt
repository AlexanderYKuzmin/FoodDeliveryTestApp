package com.kuzmin.fooddeliverytestapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzmin.fooddeliverytestapp.domain.model.AddressSuggestionResult
import com.kuzmin.fooddeliverytestapp.domain.model.address.Address
import com.kuzmin.fooddeliverytestapp.domain.model.address.QueryData
import com.kuzmin.fooddeliverytestapp.domain.usecases.GetAddressSuggestionsUseCase
import com.kuzmin.fooddeliverytestapp.domain.usecases.GetLocationDataFromDatastoreUseCase
import com.kuzmin.fooddeliverytestapp.domain.usecases.StoreLocationDataToDatastoreUseCase
import com.kuzmin.fooddeliverytestapp.domain.usecases.WriteAddressToDatastoreUseCase
import com.kuzmin.fooddeliverytestapp.util.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor(
    private val getAddressSuggestionsUseCase: GetAddressSuggestionsUseCase,
    private val getLocationDataFromDatastoreUseCase: GetLocationDataFromDatastoreUseCase,
    private val storeLocationDataToDatastoreUseCase: StoreLocationDataToDatastoreUseCase,
    private val writeAddressToDatastoreUseCase: WriteAddressToDatastoreUseCase
) : ViewModel() {

    private val _addressResult = MutableLiveData<AddressSuggestionResult>()
    val addressResult: MutableLiveData<AddressSuggestionResult> get() = _addressResult

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _addressResult.postValue(
            AddressSuggestionResult.Error(
                throwable.message ?: "Unknown error"
            )
        )
    }

    private val _searchAddressStateFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            _searchAddressStateFlow
                .debounce(300)
                .filter {
                    it.isNotEmpty()
                }
                .collectLatest {
                    getAddressSuggestions(it)
                }
        }
    }

    private suspend fun getAddressSuggestions(query: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val location = getLocationDataFromDatastoreUseCase.getLocationDataFromDatastore()

            val queryData = if (location.isNone()) {
                QueryData(
                    query = String.format("${AppConstants.DEFAULT_CITY} %s", query)
                )
            } else {
                QueryData(
                    query = query,
                    location = location
                )
            }
            _addressResult.postValue(
                AddressSuggestionResult.AddressDataSuccess(
                    getAddressSuggestionsUseCase.getAddressSuggestions(queryData)
                )
            )
        }
    }

    fun updateQuery(query: String) {
        _searchAddressStateFlow.value = query
    }

    fun writeAddressToDatastore(address: Address) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            writeAddressToDatastoreUseCase.writeAddress(address)
        }
    }
}