package com.xu.kanwanandroid.base

import android.view.View
import androidx.viewbinding.ViewBinding
import com.xu.kanwanandroid.common.util.ViewBindUtil

/**
 * Create by lxx
 * Date : 2022/2/28 9:47
 * Use by
 */
abstract class BaseBindKtActivity<VM : BaseViewModel,VB : ViewBinding> : BaseKtActivity<VM>() {

    lateinit var binding: VB

    override fun getBindLayout(): View? {
        binding = ViewBindUtil.inflate(this,layoutInflater)
        return binding.root
    }

}