package com.example.genericrecyclerlist.ui.home

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.example.genericrecyclerlist.R
import com.example.genericrecyclerlist.databinding.ItemDecorationBinding
import com.example.genericrecyclerlist.databinding.ItemFooterBinding
import com.example.genericrecyclerlist.databinding.ItemFundsBinding
import com.example.genericrecyclerlist.ui.recycler.adapter.BaseAdapter
import com.example.genericrecyclerlist.ui.recycler.adapter.BaseViewHolder
import com.example.genericrecyclerlist.ui.recycler.adapter.DataType
import java.lang.IllegalStateException

class TestAdapter: BaseAdapter<DataType, BaseViewHolder<DataType, *>>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<DataType>) {
        super.setItem(list)
        notifyDataSetChanged()
    }

    override fun createViewHolder(
        binding: ViewDataBinding,
        layoutId: Int
    ): BaseViewHolder<DataType, *> {
        return when(binding) {
            is ItemDecorationBinding -> ItemDecorationViewHolder(binding)
            is ItemFundsBinding -> ItemFundsViewHolder(binding)
            is ItemFooterBinding -> ItemFooterViewHolder(binding)
            else -> throw IllegalAccessException("Wrong binding item, please check")
        }
    }

    override fun getLayoutId(position: Int): Int {
        return when(getItem(position)) {
            is DataType.Type1 -> R.layout.item_decoration
            is DataType.Type2 -> R.layout.item_funds
            is DataType.Type3 -> R.layout.item_footer
        }
    }
}

class ItemDecorationViewHolder(
    binding: ItemDecorationBinding
): BaseViewHolder<DataType, ItemDecorationBinding>(binding) {
    override fun applyItem(item: DataType) {
        when(item) {
            is DataType.Type1 -> binding.tvTitle.text = item.name
                else -> throw IllegalStateException("wrong apply item")
        }
    }
}

class ItemFundsViewHolder(
    binding: ItemFundsBinding
): BaseViewHolder<DataType, ItemFundsBinding>(binding) {
    override fun applyItem(item: DataType) {
        when(item) {
            is DataType.Type2 -> binding.tvTitle.text = item.age.toString()
            else -> throw IllegalStateException("wrong apply item")
        }
    }
}

class ItemFooterViewHolder(
    binding: ItemFooterBinding
): BaseViewHolder<DataType, ItemFooterBinding>(binding) {
    override fun applyItem(item: DataType) {
        when(item) {
            is DataType.Type3 -> binding.tvTitle.text = item.isMale.toString()
            else -> throw IllegalStateException("wrong apply item")
        }
    }
}