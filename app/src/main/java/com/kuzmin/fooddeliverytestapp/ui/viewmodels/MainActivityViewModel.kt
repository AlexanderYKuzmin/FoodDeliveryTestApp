package com.kuzmin.fooddeliverytestapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzmin.fooddeliverytestapp.domain.model.Result
import com.kuzmin.fooddeliverytestapp.domain.model.address.Location
import com.kuzmin.fooddeliverytestapp.domain.usecases.GetAddressFromDatastoreUseCase
import com.kuzmin.fooddeliverytestapp.domain.usecases.StoreLocationDataToDatastoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val storeLocationDataToDatastoreUseCase: StoreLocationDataToDatastoreUseCase,
    private val getAddressFromDatastoreUseCase: GetAddressFromDatastoreUseCase
): ViewModel() {

    private val _result = MutableLiveData<Result>()
    val result: MutableLiveData<Result> get() = _result

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _result.postValue(
            Result.Error(throwable.message ?: "Unknown error")
        )
    }

    fun loadAddress() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {

            getAddressFromDatastoreUseCase.getAddress()
                .collectLatest {
                    _result.postValue(
                        Result.UpdateAddressSuccess(it)
                    )
                }
        }
    }

    fun storeLocationData(location: Location) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            storeLocationDataToDatastoreUseCase.storeLocationData(location)
        }
    }
}