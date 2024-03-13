package com.example.genericrecyclerlist.ui.home

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import com.example.genericrecyclerlist.R
import com.example.genericrecyclerlist.databinding.FragmentHomeBinding
import com.example.genericrecyclerlist.ui.Utils
import com.example.genericrecyclerlist.ui.base.BaseFragment
import com.example.genericrecyclerlist.ui.recycler.adapter.BaseDataType
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(), View.OnClickListener {
    private var adapter: TestAdapter? = null
//    private var randomAdapter: SingleDataAdapter? = null
    val listOfItem = listOf(
        BaseDataType.Type1("1", "Header"),
        BaseDataType.Type2("2", 20),
        BaseDataType.Type3("3", false),
        BaseDataType.Type2("4", 30),
        BaseDataType.Type1("5", "Footer"),
        BaseDataType.Type3("6", false)
    )
    override fun onBindingCreated(binding: FragmentHomeBinding) {
        adapter = TestAdapter()
//        mBinding.rvDecoration.adapter = adapter
        mViewModel.text.observe(viewLifecycleOwner) {
            val newList = listOfItem.mapIndexed { index, baseDataType ->
                if (index == 0) {
                    BaseDataType.Type1("1", it)
                } else {
                    baseDataType
                }
            }
//            adapter?.submitList(newList)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.button -> {
                if ((adapter?.itemCount ?: 0) <= 20) {
//                    mBinding.tvCount.text = Utils.retryTimes.toString()
                    adapter?.submitList(
                        listOfItem
                    )
                }
            }
            R.id.show -> mViewModel._text.safePutValue("test")
            R.id.fragcall -> {
                val index = 2
                if(index in 1..10 step 2) {
                    return
                }
            }
        }
    }

    override fun setClickListener() {
        mBinding.clickListener = this
    }
}