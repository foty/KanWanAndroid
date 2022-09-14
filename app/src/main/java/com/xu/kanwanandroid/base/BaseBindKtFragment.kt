package com.xu.kanwanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.xu.kanwanandroid.common.util.ViewBindUtil

/**
 * Create by lxx
 * Date : 2022/2/28 14:04
 * Use by
 */
abstract class BaseBindKtFragment<VM : BaseViewModel, VB : ViewBinding> : BaseKtFragment<VM>() {

    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getLayoutBind(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = ViewBindUtil.inflate(this, inflater, container, false)
        if (_binding == null) throw IllegalArgumentException("binding == null")
        return binding.root
    }
}