//package com.example.genericrecyclerlist.ui.home
//
//import androidx.databinding.ViewDataBinding
//import com.example.genericrecyclerlist.R
//import com.example.genericrecyclerlist.databinding.ItemSingleBinding
//import com.example.genericrecyclerlist.ui.recycler.adapter.BaseAdapter
//import com.example.genericrecyclerlist.ui.recycler.adapter.BaseDataType
//import com.example.genericrecyclerlist.ui.recycler.adapter.BaseViewHolder
//
//class SingleDataAdapter: BaseAdapter<BaseDataType, BaseViewHolder<BaseDataType, *>>() {
//    override fun createViewHolder(
//        binding: ViewDataBinding,
//        layoutId: Int
//    ): BaseViewHolder<BaseDataType, *> {
//        return SingleDataViewHolder(binding as ItemSingleBinding)
//    }
//
//
//    override fun getLayoutId(position: Int): Int {
//        return R.layout.item_single
//    }
//
//    override fun areContentTheSame(oldItem: BaseDataType, newItem: BaseDataType): Boolean {
//        return oldItem. == newItem.data &&
//                oldItem.data == newItem.data
//    }
//
//    override fun areItemTheSame(oldItem: BaseDataType, newItem: BaseDataType): Boolean {
//        return oldItem == newItem
//    }
//}
//
//data class RandomBean(
//    val id: String,
//    val name: String,
//)