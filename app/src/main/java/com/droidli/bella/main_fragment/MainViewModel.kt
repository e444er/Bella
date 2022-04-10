package com.droidli.bella.main_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidli.domain.use_case.GetAllUseCase
import com.droidli.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetAllUseCase
) : ViewModel() {

    private val _getAllUseCase = MutableStateFlow(MainState())
    val getAllUseCase: StateFlow<MainState> = _getAllUseCase

    fun getList() {
        useCase.invoke().onEach {
            when (it) {
                is Resource.Loading -> {
                    _getAllUseCase.value = MainState(isLoading = true)
                }
                is Resource.Error -> {
                    _getAllUseCase.value = MainState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _getAllUseCase.value = MainState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}