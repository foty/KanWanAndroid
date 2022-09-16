package com.xu.kanwanandroid.core.main.adapter

import androidx.fragment.app.FragmentPagerAdapter
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.util.ArrayList

/**
 * Create by lxx
 * Date : 2022/9/15 14:52
 * Use by
 */
class TabFragmentPagerAdapter : FragmentPagerAdapter {
    private var fragments: List<Fragment>
    private var titles: Array<String>?

    constructor(
        fm: FragmentManager?,
        fragments: ArrayList<Fragment>,
        titles: Array<String>?
    ) : super(
        fm!!
    ) {
        this.fragments = fragments
        this.titles = titles
    }

    constructor(fm: FragmentManager?, fragments: List<Fragment>) : super(
        fm!!
    ) {
        this.fragments = fragments
        titles = null
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    // 动态设置标题的方法
    fun setPageTitle(position: Int, title: String) {
        if (position >= 0 && position < titles!!.size) {
            titles!![position] = title
            notifyDataSetChanged()
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //super.destroyItem(container, position, object);
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (null == titles) {
            super.getPageTitle(position)
        } else {
            titles!![position]
        }
    }
}