package com.example.genericrecyclerlist.ui.base

import android.os.Looper
import androidx.lifecycle.MediatorLiveData
import java.util.Objects

class CommonLiveData<T>(): MediatorLiveData<T>() {
    private var currentValue: T? = null
    var defaultValue: T? = null

    /**
     * 需要不要在第一次订阅就返回默认值，不需要就只设默认值
     */
    constructor(value: T?) : this() {
        value?.let { put(value) }
    }

    private fun put(item: T?): Boolean {
        this.currentValue = item
        if (Looper.myLooper() == Looper.getMainLooper()) {
            setValue(item)
        } else {
            postValue(item)
        }
        return true
    }

    /**
     * 检查不等于当下的值和不是null才设置新的值
     */
    fun safePutValue(item: T): Boolean {
        if (Objects.equals(currentValue, item)) {
            return false
        }
        return put(item)
    }

    fun get(): T? = currentValue ?: value

    /**
     * 检视当前的值
     */
    @Suppress("UNCHECKED_CAST")
    fun peek(): T {
        currentValue?.let { return currentValue as T }
        defaultValue?.let { return defaultValue as T }
        value.let { return value as T }
    }

    /**
     * 当前值和默认值不等于null
     */
    fun hasValue() = defaultValue != null || currentValue != null

    /**
     * 默认值不等于null
     */
    fun hasDefaultValue() = defaultValue != null

    fun notifyObservers() {
        currentValue?.let { put(it) }
    }

    fun clear() {
        defaultValue = null
        currentValue = null
    }
}