package com.xu.kanwanandroid.login

import com.xu.kanwanandroid.R
import com.xu.kanwanandroid.base.BaseBindKtActivity
import com.xu.kanwanandroid.base.EmptyViewModel
import com.xu.kanwanandroid.databinding.ActSplashBinding

/**
 * Create by lxx
 * Date : 2022/9/13 15:47
 * Use by
 */
class SplashActivity : BaseBindKtActivity<EmptyViewModel, ActSplashBinding>() {


    override fun initView() {
        overridePendingTransition(R.anim.zoom_small_in, R.anim.zoom_small_out)

    }

    override fun initData() {

    }

    override fun addListener() {

    }
}