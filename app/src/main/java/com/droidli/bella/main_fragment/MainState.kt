package com.droidli.bella.main_fragment

import com.droidli.domain.model.BellaInfo

data class MainState(
    val data: List<BellaInfo>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)
