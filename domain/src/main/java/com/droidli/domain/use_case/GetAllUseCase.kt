package com.droidli.domain.use_case

import com.droidli.domain.model.BellaInfo
import com.droidli.domain.repository.BellaRepository
import com.droidli.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllUseCase @Inject constructor(
    private val repository: BellaRepository
) {
    operator fun invoke(): Flow<Resource<List<BellaInfo>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getAllBellaList()
            emit(Resource.Success(data = data))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown error"))
        }
    }
}