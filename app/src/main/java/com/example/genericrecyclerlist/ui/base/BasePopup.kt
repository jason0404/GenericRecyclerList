package com.example.genericrecyclerlist.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.genericrecyclerlist.databinding.ItemFundsBinding

abstract class BasePopup<T: ViewDataBinding>(context: Context, layoutId: Int): PopupWindow() {

    private var _binding: T? = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        layoutId,
        null,
        false
    )

    protected val popupWindow by lazy {
        PopupWindow(_binding?.root)
    }

    init {
        popupWindow.contentView = _binding?.root
        popupWindow.width =  WindowManager.LayoutParams.MATCH_PARENT //ScreenUtils.dip2px(context, 340f)
        popupWindow.height = WindowManager.LayoutParams.WRAP_CONTENT
        popupWindow.isFocusable = true
    }
}

class ItemPopup(context: Context, layoutId: Int): BasePopup<ItemFundsBinding>(context, layoutId) {

}