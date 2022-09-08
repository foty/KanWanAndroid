package com.xu.kanwanandroid.base

import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.xu.kanwanandroid.R
import java.io.File


/**
 * Create by lxx
 * Date : 2020/12/22 17:50
 * Use by
 */
abstract class BaseKtActivity<VM : BaseViewModel> : FragmentActivity() {

    protected val viewModel by lazy {
        //lifecycle 2.2以下
//        ViewModelProviders.of(this)
//        .get(ReflectUtil.getGenericClass<VM>(this))

        //lifecycle 2.2
        ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory(application))
                .get(ReflectUtil.getGenericClass<VM>(this))
    }


    /**
     * 隐藏软键盘
     */
    protected fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }
}