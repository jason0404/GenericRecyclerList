//package com.example.genericrecyclerlist.ui.home
//
//import com.example.genericrecyclerlist.databinding.ItemSingleBinding
//import com.example.genericrecyclerlist.ui.recycler.adapter.BaseDataBean
//import com.example.genericrecyclerlist.ui.recycler.adapter.BaseViewHolder
//
//class SingleDataViewHolder(
//    binding: ItemSingleBinding
//): BaseViewHolder<BaseDataBean<RandomBean>, ItemSingleBinding>(binding) {
//    override fun applyItem(item: BaseDataBean<RandomBean>) {
//        binding.tvTitle.text = item.data?.name
//    }
//}