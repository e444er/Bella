package com.droidli.bella.detail_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.droidli.bella.R
import com.droidli.bella.databinding.DetailFragmentBinding
import com.droidli.domain.model.BellaInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val binding by viewBinding(DetailFragmentBinding::bind)
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bellaArgs = args.bellaArgs
        bellaUi(bellaArgs)
    }

    private fun bellaUi(bellaArgs: BellaInfo) {
        binding.apply {
            Glide.with(root).load(bellaArgs.image).into(profPic)
            tvAlias.text = bellaArgs.alias
            tvName.text = bellaArgs.name
            tvOccupation.text = bellaArgs.occupation
            tvGender.text = bellaArgs.gender
            tvRomance.text = bellaArgs.romance
            tvFamily.text = bellaArgs.family
            tvFirstAppearance.text = bellaArgs.first_appearance
            tvLastAppearance.text = bellaArgs.last_appearance
            tvPlayedBy.text = bellaArgs.played_by
        }
    }
}