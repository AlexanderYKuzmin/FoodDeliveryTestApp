package com.kuzmin.fooddeliverytestapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzmin.fooddeliverytestapp.domain.model.AddressSuggestionResult
import com.kuzmin.fooddeliverytestapp.domain.model.address.Address
import com.kuzmin.fooddeliverytestapp.domain.model.address.Location
import com.kuzmin.fooddeliverytestapp.domain.model.address.QueryData
import com.kuzmin.fooddeliverytestapp.domain.usecases.GetAddressSuggestionsUseCase
import com.kuzmin.fooddeliverytestapp.domain.usecases.GetLocationDataFromDatastoreUseCase
import com.kuzmin.fooddeliverytestapp.domain.usecases.WriteAddressToDatastoreUseCase
import com.kuzmin.fooddeliverytestapp.util.AppConstants
import com.kuzmin.fooddeliverytestapp.util.exceptions.UndefinedLocationException
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
    private val writeAddressToDatastoreUseCase: WriteAddressToDatastoreUseCase
) : ViewModel() {

    private var location: Location? = null

    private val _addressResult = MutableLiveData<AddressSuggestionResult>()
    val addressResult: MutableLiveData<AddressSuggestionResult> get() = _addressResult

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _addressResult.postValue(
            AddressSuggestionResult.Error(
                throwable
            )
        )
    }

    private val _searchAddressStateFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {

            launch(Dispatchers.IO + coroutineExceptionHandler) {
                location = getLocationDataFromDatastoreUseCase.getLocationDataFromDatastore()
            }

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
            val queryData = if (location == null || location!!.isNone()) {
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

    fun getAddressByLocation() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            if (location == null || location!!.isNone()) throw UndefinedLocationException()
            _addressResult.postValue(
                AddressSuggestionResult.AddressByLocationSuccess(
                    getAddressSuggestionsUseCase.getAddressByLocation(location!!)
                )
            )
        }
    }

    fun writeAddressToDatastore(address: Address) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            writeAddressToDatastoreUseCase.writeAddress(address)
        }
    }
}