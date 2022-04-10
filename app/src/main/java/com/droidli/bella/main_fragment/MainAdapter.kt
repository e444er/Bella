package com.droidli.bella.main_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.droidli.bella.databinding.ItemMainBinding
import com.droidli.domain.model.BellaInfo

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

    var onClickListener: ((BellaInfo) -> Unit)? = null


    private class DifferCallback : DiffUtil.ItemCallback<BellaInfo>() {
        override fun areItemsTheSame(oldItem: BellaInfo, newItem: BellaInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BellaInfo, newItem: BellaInfo): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, DifferCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bella = differ.currentList[position]
        holder.binding.apply {
            tvName.text = bella.name
            tvName.text = bella.alias
            Glide.with(root)
                .load(bella.image)
                .into(profPic)
        }
        holder.binding.root.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(bella)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}