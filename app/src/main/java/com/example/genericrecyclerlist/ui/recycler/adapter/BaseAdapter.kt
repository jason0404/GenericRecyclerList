package com.example.genericrecyclerlist.ui.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<ITEM: DataType, VH: BaseViewHolder<ITEM, *>>: RecyclerView.Adapter<VH>() {

    protected var listOfItem: List<ITEM>? = null

    protected abstract fun createViewHolder(binding: ViewDataBinding, @LayoutRes layoutId: Int): VH
    @LayoutRes
    abstract fun getLayoutId(position: Int): Int
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)

        val binding: ViewDataBinding = DataBindingUtil.inflate(
            inflater,
            viewType,
            parent,
            false
        )

        return createViewHolder(binding, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.applyItem(getItem(position))
    }

    fun setItem(list: List<ITEM>) {
        listOfItem = list
    }

    override fun getItemCount(): Int {
        return listOfItem?.count() ?: 0
    }

    fun getItem(position: Int): ITEM {
        return listOfItem!![position]
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position)
    }
}