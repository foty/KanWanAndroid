package com.xu.kanwanandroid.base

import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xu.kanwanandroid.common.util.ViewBindUtil

/**
 * Create by lxx
 * Date : 2022/3/1 15:59
 * Use by
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseBindQuickAdapter<T, VB : ViewBinding> :
    BaseQuickAdapter<T, BaseBindQuickAdapter.BaseBindViewHolder<VB>>(-1) {

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int) =
        BaseBindViewHolder<VB>(ViewBindUtil.inflate<VB>(this, parent))


    class BaseBindViewHolder<VB>(private val binding: ViewBinding) : BaseViewHolder(binding.root) {
              constructor(itemView: View) : this(ViewBinding { itemView })

        val bind : VB get() = binding as VB
    }
}