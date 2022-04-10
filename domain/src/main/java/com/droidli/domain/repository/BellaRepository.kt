package com.droidli.domain.repository

import com.droidli.domain.model.BellaInfo

interface BellaRepository {

    suspend fun getAllBellaList(): List<BellaInfo>

}