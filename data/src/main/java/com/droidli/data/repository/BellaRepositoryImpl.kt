package com.droidli.data.repository

import com.droidli.data.remote.BellaApi
import com.droidli.domain.model.BellaInfo
import com.droidli.domain.repository.BellaRepository
import javax.inject.Inject

class BellaRepositoryImpl @Inject constructor(
    private val api:BellaApi
): BellaRepository {

    override suspend fun getAllBellaList(): List<BellaInfo> {
        return api.getBella()
    }
}