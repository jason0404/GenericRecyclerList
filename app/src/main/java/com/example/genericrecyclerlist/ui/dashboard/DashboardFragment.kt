package com.example.genericrecyclerlist.ui.dashboard

import android.view.View
import com.example.genericrecyclerlist.R
import com.example.genericrecyclerlist.databinding.FragmentDashboardBinding
import com.example.genericrecyclerlist.ui.Utils
import com.example.genericrecyclerlist.ui.base.BaseFragment

class DashboardFragment : BaseFragment<DashboardViewModel, FragmentDashboardBinding>(), View.OnClickListener {

private var _binding: FragmentDashboardBinding? = null

    override fun setClickListener() {
        mBinding.clickListener = this
    }

    override fun onBindingCreated(binding: FragmentDashboardBinding) {
        binding.button.setOnClickListener {
            binding.tvCount.text = Utils.retryTimes.toString()
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.button -> {
                showLoading("Called From Dashboard")
            }
        }
    }
}