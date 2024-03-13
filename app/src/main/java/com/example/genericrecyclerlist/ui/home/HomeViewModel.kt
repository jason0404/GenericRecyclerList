package com.example.genericrecyclerlist.ui.home

import android.media.metrics.Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.genericrecyclerlist.ui.base.BaseViewModel
import com.example.genericrecyclerlist.ui.base.CommonLiveData

class HomeViewModel : BaseViewModel() {

    val _text = CommonLiveData("test")
    private val testLiveData: MutableLiveData<Event>? = null
    val text: LiveData<String> = _text
}