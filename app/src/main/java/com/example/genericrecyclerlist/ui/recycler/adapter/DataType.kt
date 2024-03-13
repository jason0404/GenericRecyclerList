package com.example.genericrecyclerlist.ui.recycler.adapter

import DiffItem
import androidx.recyclerview.widget.DiffUtil

sealed class BaseDataType {
    data class Type1(
        val id: String,
        var name: String
    ): BaseDataType()

    data class Type2(
        val id: String,
        val age: Int,
    ): BaseDataType()

    data class Type3(
        val id: String,
        val isMale: Boolean
    ): BaseDataType()
}



//open class BaseDataClass(
//    val itemId: String,
//    val itemTag: String
//): DiffItem<BaseDataClass> {
//    override fun areContentTheSame(oldItem: BaseDataClass): Boolean {
//        return itemId == oldItem.itemId
//    }
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//        if (!super.equals(other)) return false
//
//        other as HomeItem
//
//        if (itemId != other.itemId) return false
//        return itemTag == other.itemTag
//    }
//
//    override fun hashCode(): Int {
//        var result = itemTag.hashCode()
//        result = 31 * result + itemTag.hashCode()
//        return result
//    }
//}

data class HomeItem(
    val id: String,
    val tag: String
): DiffUtil.ItemCallback<HomeItem>() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as HomeItem

        if (id != other.id) return false
        return tag == other.tag
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + tag.hashCode()
        return result
    }

    override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem.id == newItem.id && oldItem.tag == newItem.tag
    }

    override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem == newItem
    }
}

