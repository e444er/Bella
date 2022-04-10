package com.droidli.bella.main_fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.droidli.bella.R
import com.droidli.bella.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val binding by viewBinding(MainFragmentBinding::bind)
    private val viewModel: MainViewModel by viewModels()
    private var _adapter: MainAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getList()
        binding.rvQuestions.apply {
            _adapter = MainAdapter()
            adapter = _adapter
            setHasFixedSize(true)
        }

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.getAllUseCase.collect { bellaList ->
                if (bellaList.isLoading) {
                    binding.rvQuestions.isVisible = false
                    binding.tvHeaderTitle.isVisible = false
                    binding.progressBar.isVisible = true
                }
                if (bellaList.error.isNotBlank()) {
                    binding.rvQuestions.isVisible = false
                    binding.tvHeaderTitle.isVisible = false
                    binding.progressBar.isVisible = true
                }
                bellaList.data?.let {
                    binding.progressBar.isVisible = false
                    binding.rvQuestions.isVisible = true
                    binding.tvHeaderTitle.isVisible = true
                    _adapter?.differ?.submitList(it.toMutableList())
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _adapter = null
    }
}