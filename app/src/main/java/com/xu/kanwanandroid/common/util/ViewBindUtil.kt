@file:Suppress("UNCHECKED_CAST", "unused")

package com.xu.kanwanandroid.common.util

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.BuildConfig
import androidx.viewbinding.ViewBinding

/**
 * Create by lxx
 * Date : 2022/2/28 11:13
 * Use by
 */
object ViewBindUtil {

    @JvmStatic
    fun <VB : ViewBinding> inflate(activity: Any, inflater: LayoutInflater) =
        findClass<VB>(activity) { it.getMethod("inflate", LayoutInflater::class.java).invoke(null, inflater) as VB }

    @JvmStatic
    fun <VB : ViewBinding> inflate(adapter: Any, parent: ViewGroup) =
        findClass<VB>(adapter) { it.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java,
            Boolean::class.java).invoke(null, LayoutInflater.from(parent.context), parent, false) as VB }

    @JvmStatic
    fun <VB : ViewBinding> inflate(fragment: Any, inflater: LayoutInflater, parent: ViewGroup?, attach: Boolean) =
        findClass<VB>(fragment) { it.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java,
                Boolean::class.java).invoke(null, inflater, parent, attach) as VB }


    private fun <VB : ViewBinding> findClass(o: Any, block: (Class<VB>) -> VB): VB {
        ReflectUtil.getGenericClassList(o).forEach {
            if (BuildConfig.DEBUG) {
                Log.e("lxx", it.toString())
            }
            if (it.toString().endsWith("Binding")) {
                return block.invoke(it as Class<VB>)
            }
        }
        throw IllegalArgumentException("没有找到ViewBinding类")
    }
}