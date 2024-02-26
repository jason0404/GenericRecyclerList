package com.example.genericrecyclerlist.ui.home

import android.view.View
import com.example.genericrecyclerlist.R
import com.example.genericrecyclerlist.databinding.FragmentHomeBinding
import com.example.genericrecyclerlist.ui.Utils
import com.example.genericrecyclerlist.ui.base.BaseFragment
import com.example.genericrecyclerlist.ui.recycler.adapter.DataType

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(), View.OnClickListener {
    private var adapter: TestAdapter? = null
    override fun onBindingCreated(binding: FragmentHomeBinding) {
        adapter = TestAdapter()
        mBinding.rvDecoration.adapter = adapter
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.button -> {
                if (adapter?.itemCount!! <= 20) {
                    mBinding.tvCount.text = Utils.retryTimes.toString()
                    adapter?.submitList(
                        listOf(
                            DataType.Type1("1", "Header"),
                            DataType.Type2("2", 20),
                            DataType.Type3("3", false),
                            DataType.Type2("4", 30),
                            DataType.Type1("5", "Footer"),
                            DataType.Type3("6", false)
                        )
                    )
                }
            }
            R.id.show -> mViewModel.showLoading("Called from ViewModel")
            R.id.fragcall -> showLoading("Called from Fragment")
        }
    }

    override fun setClickListener() {
        mBinding.clickListener = this
    }
}