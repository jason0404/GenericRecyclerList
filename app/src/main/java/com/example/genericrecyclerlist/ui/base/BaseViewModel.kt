package com.example.genericrecyclerlist.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseViewModel: ViewModel() {
    val _showLoading: MutableLiveData<String> = MutableLiveData("")
    val loadingShowed: LiveData<String>
        get() = _showLoading
    fun showLoading(msg: String) {
        _showLoading.value = msg
    }
}

abstract class BaseVmFragment<VM: BaseViewModel>: Fragment() {
    abstract fun showLoading(msg: String)
    lateinit var mViewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    @Suppress("UNCHECKED_CAST")
    fun <VM> getVmClazz(obj: Any): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
    }
}

abstract class BaseVmFragmentV2<VM: BaseViewModelV2>: Fragment() {

    lateinit var mViewModelV2: VM
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createViewModel {
            mViewModelV2 = it
            it.subscribeEvent()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun createViewModel(onViewModelCreated: (VM) -> Unit) {
        onViewModelCreated(ViewModelProvider(this).get(getVmClazz(this)))
    }

    @Suppress("UNCHECKED_CAST")
    fun <VM> getVmClazz(obj: Any): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
    }
}