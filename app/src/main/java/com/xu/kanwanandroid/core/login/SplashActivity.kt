package com.xu.kanwanandroid.core.login

import android.content.Intent
import android.os.Build
import android.window.SplashScreenView
import com.xu.kanwanandroid.R
import com.xu.kanwanandroid.base.BaseBindKtActivity
import com.xu.kanwanandroid.base.EmptyViewModel
import com.xu.kanwanandroid.core.main.activity.MainActivity
import com.xu.kanwanandroid.databinding.ActSplashBinding


/**
 * Create by lxx
 * Date : 2022/9/13 15:47
 * Use by
 */
class SplashActivity : BaseBindKtActivity<EmptyViewModel, ActSplashBinding>() {


    override fun initView() {
        overridePendingTransition(R.anim.zoom_small_in, R.anim.zoom_small_out)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { view: SplashScreenView? ->

            }
        }


    }

    override fun initData() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun addListener() {

    }
}