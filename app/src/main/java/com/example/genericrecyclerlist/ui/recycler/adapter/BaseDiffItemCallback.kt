//package com.example.genericrecyclerlist.ui.recycler.adapter
//
//import androidx.recyclerview.widget.DiffUtil
//
//class BaseDiffItemCallback<ITEM: DiffItem<ITEM>> : DiffUtil.ItemCallback<ITEM>() {
//
//    var changePayload:
//
//    override fun areItemsTheSame(oldItem: ITEM, newItem: ITEM): Boolean {
//
//    }
//
//    override fun areContentsTheSame(oldItem: ITEM, newItem: ITEM): Boolean {
//
//    }
//
//    override fun getChangePayload(oldItem: ITEM, newItem: ITEM): Any? {
//        return
//    }
//
//}
//
interface DiffItem<ITEM> {
    fun areContentTheSame(oldItem: ITEM): Boolean
    fun areItemTheSame(oldItem: ITEM): Boolean = equals(oldItem)
}
//
//class ChangePayloadResolver<ITEM> {
//    fun resolve() {
//
//    }
//}
//
//class ChangePayload {
//    companion object {
//        val EMPTY = ChangePayload()
//    }
//}