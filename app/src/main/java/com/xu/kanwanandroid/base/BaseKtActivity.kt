package com.xu.kanwanandroid.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.xu.kanwanandroid.common.util.ReflectUtil


/**
 * Create by lxx
 * Date : 2020/12/22 17:50
 * Use by
 */
abstract class BaseKtActivity<VM : BaseViewModel> : FragmentActivity() {

    protected val viewModel by lazy {
        //lifecycle 2.2以下
//        ViewModelProviders.of(this)
//        .get(com.xu.kanwanandroid.common.util.ReflectUtil.getGenericClass<VM>(this))

        //lifecycle 2.2
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(ReflectUtil.getGenericClass<VM>(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val layout: View = getBindLayout() ?: throw IllegalArgumentException("没有根布局")
        setContentView(layout)

    }


    /**
     * ViewBind view
     */
    open fun getBindLayout(): View? {
        return null
    }


    /**
     * 隐藏软键盘
     */
    protected fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }

    /**
     * 初始化View
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化监听器
     */
    abstract fun addListener()
}