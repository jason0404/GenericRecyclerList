package com.example.genericrecyclerlist.ui.home

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.example.genericrecyclerlist.R
import com.example.genericrecyclerlist.databinding.ItemDecorationBinding
import com.example.genericrecyclerlist.databinding.ItemFooterBinding
import com.example.genericrecyclerlist.databinding.ItemFundsBinding
import com.example.genericrecyclerlist.ui.recycler.adapter.BaseAdapter
import com.example.genericrecyclerlist.ui.recycler.adapter.BaseDataType
import com.example.genericrecyclerlist.ui.recycler.adapter.BaseViewHolder
import java.lang.IllegalStateException

class TestAdapter: BaseAdapter<BaseDataType, BaseViewHolder<BaseDataType, *>>() {
    
    fun submitList(list: List<BaseDataType>) {
        super.setItem(list)
    }

    override fun createViewHolder(
        binding: ViewDataBinding,
        layoutId: Int
    ): BaseViewHolder<BaseDataType, *> {
        return when(binding) {
            is ItemDecorationBinding -> ItemDecorationViewHolder(binding)
            is ItemFundsBinding -> ItemFundsViewHolder(binding)
            is ItemFooterBinding -> ItemFooterViewHolder(binding)
            else -> throw IllegalAccessException("Wrong binding item, please check")
        }
    }

    override fun getLayoutId(position: Int): Int {
        return when(getItem(position)) {
            is BaseDataType.Type1 -> R.layout.item_decoration
            is BaseDataType.Type2 -> R.layout.item_funds
            is BaseDataType.Type3 -> R.layout.item_footer
        }
    }

    val bool: Boolean = false

    override fun areContentTheSame(oldItem: BaseDataType, newItem: BaseDataType): Boolean {
        bool.not()
        return when {
            oldItem is BaseDataType.Type1 && newItem is BaseDataType.Type1 -> return oldItem.name == newItem.name
            oldItem is BaseDataType.Type2 && newItem is BaseDataType.Type2 -> return oldItem.age == newItem.age
            oldItem is BaseDataType.Type3 && newItem is BaseDataType.Type3 -> return oldItem.isMale == newItem.isMale
            else -> false
        }
    }

    override fun areItemTheSame(oldItem: BaseDataType, newItem: BaseDataType): Boolean {
        return when {
            oldItem is BaseDataType.Type1 && newItem is BaseDataType.Type1 -> oldItem == newItem
            oldItem is BaseDataType.Type2 && newItem is BaseDataType.Type2 -> oldItem == newItem
            oldItem is BaseDataType.Type3 && newItem is BaseDataType.Type3 -> oldItem == newItem
            else -> false
        }
    }
}

class ItemDecorationViewHolder(
    binding: ItemDecorationBinding
): BaseViewHolder<BaseDataType , ItemDecorationBinding>(binding) {
    override fun applyItem(item: BaseDataType) {
        val `data` = item as BaseDataType.Type1
        binding.tvTitle.text = data.name
    }
}

class ItemFundsViewHolder(
    binding: ItemFundsBinding
): BaseViewHolder<BaseDataType, ItemFundsBinding>(binding) {
    override fun applyItem(item: BaseDataType) {
        val `data` = item as BaseDataType.Type2
        binding.tvTitle.text = data.age.toString()
    }
}

class ItemFooterViewHolder(
    binding: ItemFooterBinding
): BaseViewHolder<BaseDataType, ItemFooterBinding>(binding) {
    override fun applyItem(item: BaseDataType) {
        val `data` = item as BaseDataType.Type3
        binding.tvTitle.text = data.isMale.toString()
    }
}