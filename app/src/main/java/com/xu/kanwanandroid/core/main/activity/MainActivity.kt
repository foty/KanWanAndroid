package com.xu.kanwanandroid.core.main.activity

import com.xu.kanwanandroid.base.BaseBindKtActivity
import com.xu.kanwanandroid.base.EmptyViewModel
import com.xu.kanwanandroid.core.main.adapter.TabFragmentPagerAdapter
import com.xu.kanwanandroid.core.main.fragment.MainFragment
import com.xu.kanwanandroid.databinding.ActMainBinding

/**
 * Create by lxx
 * Date : 2022/9/14 14:52
 * Use by
 */
class MainActivity : BaseBindKtActivity<EmptyViewModel, ActMainBinding>() {


    override fun initView() {
        val fragments = listOf(MainFragment(), MainFragment(), MainFragment())

        binding.viewPager.adapter = TabFragmentPagerAdapter(supportFragmentManager, fragments)
    }

    override fun initData() {

    }

    override fun addListener() {

    }
}