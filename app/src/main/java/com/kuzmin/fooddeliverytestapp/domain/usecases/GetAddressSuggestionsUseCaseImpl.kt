package com.kuzmin.fooddeliverytestapp.domain.usecases

import com.kuzmin.fooddeliverytestapp.domain.Repository
import com.kuzmin.fooddeliverytestapp.domain.model.address.Address
import com.kuzmin.fooddeliverytestapp.domain.model.address.QueryData
import javax.inject.Inject

class GetAddressSuggestionsUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetAddressSuggestionsUseCase {

    override suspend fun getAddressSuggestions(queryData: QueryData) =
        repository.getAddressSuggestions(queryData)

}

interface GetAddressSuggestionsUseCase {
    suspend fun getAddressSuggestions(queryData: QueryData): List<Address>
}
