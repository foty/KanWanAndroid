package com.xu.kanwanandroid.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xu.kanwanandroid.common.util.ReflectUtil

/**
 * Create by lxx
 * Date : 2021/1/5 10:17
 * Use by
 */
abstract class BaseKtFragment<VM : BaseViewModel> : Fragment() {

    private val TAG = "BaseKtFragment"
    lateinit var viewModel: VM

    private var mRootView: View? = null
    protected val _mRootView: View get() = mRootView!!

    /**
     * 默认开启懒加载
     */
    private var needLazy = true

    /**
     * 是否加载数据，只加载一次
     */
    protected var firstLoadData = true
    private var canVisibleToUser = false
    private var hasResume = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReflectUtil.getGenericClass<VM>(this))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (null == mRootView) {
            val bind = getLayoutBind(inflater, container) ?: throw IllegalArgumentException("没有根布局")
            mRootView = bind
            initView(_mRootView, savedInstanceState)
        }
        return mRootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view, savedInstanceState)
        firstLoadData = true
        Log.d(TAG, "onViewCreated")
        addListener()
    }

    override fun onResume() {
        super.onResume()
        hasResume = true
        Log.d(TAG, "onResume ")
        lazyLoad()
    }

    /**
     * 此方法执行优先于Fragment的生命周期
     * 适用于viewpager+fragment
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        canVisibleToUser = isVisibleToUser
        Log.d(TAG, "setUserVisibleHint $isVisibleToUser")
        lazyLoad()
    }

    /**
     * 适用于fragment的show()/hide()
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.d(TAG, "hidden $hidden")
        canVisibleToUser = !hidden
        lazyLoad()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
        canVisibleToUser = false
        hasResume = false
        firstLoadData = true
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy ")
    }

    private fun lazyLoad() {
        Log.d(TAG, "loadData + $this")
        if (needLazy && firstLoadData && hasResume && canVisibleToUser) {
            loadData()
            firstLoadData = false
        }
    }

    abstract fun getLayoutBind(inflater: LayoutInflater, container: ViewGroup?): View

    abstract fun initView(view: View, savedInstanceState: Bundle?)

    abstract fun addListener()

    abstract fun loadData()
}