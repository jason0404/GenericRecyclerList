package com.example.genericrecyclerlist.ui.recycler.adapter

sealed class DataType {
    data class Type1(
        val id: String,
        val name: String
    ): DataType()

    data class Type2(
        val id: String,
        val age: Int,
    ): DataType()

    data class Type3(
        val id: String,
        val isMale: Boolean
    ): DataType()
}