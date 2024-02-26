package com.example.genericrecyclerlist.ui.recycler.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in ITEM, out VDB: ViewDataBinding>(
    val binding: VDB
): RecyclerView.ViewHolder(binding.root) {
    abstract fun applyItem(item: ITEM)
}