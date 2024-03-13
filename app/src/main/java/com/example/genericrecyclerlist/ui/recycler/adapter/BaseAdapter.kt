package com.example.genericrecyclerlist.ui.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.ConcurrentHashMap

abstract class BaseAdapter<ITEM, VH: BaseViewHolder<ITEM, *>>: RecyclerView.Adapter<VH>() {
    var diffResult: DiffResult? = null
    private var listOfItem: List<ITEM>? = null

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

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }

    fun setItem(list: List<ITEM>) {
        val diffResult = calculateDiff(listOfItem, list)
        listOfItem = list
        diffResult.dispatchUpdatesTo(this)
    }

    abstract fun areItemTheSame(oldItem: ITEM, newItem: ITEM): Boolean
    abstract fun areContentTheSame(oldItem: ITEM, newItem: ITEM): Boolean

    private fun calculateDiff(oldItems: List<ITEM>? = emptyList(), newItems: List<ITEM>): DiffResult {
        return DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = oldItems?.size ?: 0
            override fun getNewListSize() = newItems.size
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                oldItems?.let { listItem ->
                    return areContentTheSame(listItem[oldItemPosition], newItems[newItemPosition])
                }
                return false
            }
            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                oldItems?.let { listItem ->
                    return areContentTheSame(listItem[oldItemPosition], newItems[newItemPosition])
                }
                return false
            }
        })
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