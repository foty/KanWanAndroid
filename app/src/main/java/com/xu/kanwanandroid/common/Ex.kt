package com.xu.kanwanandroid.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.gson.JsonParseException
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.xu.kanwanandroid.base.BaseViewModel
import kotlinx.coroutines.launch
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * Create by lxx
 * Date : 2020/12/22 17:07
 * Use by
 */


fun BaseViewModel.asyncRequest(block: suspend () -> Unit) {
    viewModelScope.launch {
        runCatching {
            block()
        }.onSuccess {
        }.onFailure {
            it.printStackTrace()
            //异常捕获。。。
        }
    }
}


fun BaseViewModel.asyncRequest(block: suspend () -> Unit, error: suspend () -> Unit) {
    viewModelScope.launch {
        runCatching {
            block()
        }.onSuccess {
        }.onFailure {
            it.printStackTrace()
            //异常捕获。。。
            error()
        }
    }
}

fun <C> BaseViewModel.asyncRequest(block: suspend () -> Unit, result: MutableLiveData<C>) {
    viewModelScope.launch {
        runCatching {
            block()
        }.onSuccess {
        }.onFailure {
            it.printStackTrace()
            //异常捕获。。。
            result.value = null
        }
    }
}


fun BaseViewModel.launch(block: suspend () -> Unit, error: suspend (String) -> Unit) {

    viewModelScope.launch {
        runCatching {
            block()
        }.onSuccess {
        }.onFailure { e ->
            e.printStackTrace()
            var msg = "";
            if (e is HttpException) {
                //HTTP错误
                msg = "网络错误"
            } else if (e is ConnectException || e is UnknownHostException) {
                //连接错误
                msg = "地址连接失败"
            } else if (e is InterruptedIOException) {
                //连接超时
                msg = "地址连接超时"

            } else if (e is JsonParseException || e is JSONException || e is ParseException) {
                //解析错误
                msg = "解析数据失败"

            } else {
                msg = "未知错误"
            }
            error(msg)
        }
    }
}



/**
 * 处理SmartRefreshLayout的分页加载
 * @param pageNum 第一页，默认为1
 */
fun SmartRefreshLayout.helper(pageNum: Int, hasMore: Boolean, block: () -> Unit) {
//    this.setEnableLoadMore(hasMore)
//    when {
//        (pageNum != 1) -> this.finishLoadMore()
//    }
//    block()
}

/**
 * 处理SmartRefreshLayout的分页加载情况
 * @param pageNum 第一页，默认为1
 * @return 全新list
 */
fun <T> SmartRefreshLayout.helper(
    pageNum: Int, pageSize: Int, list: MutableList<T>,
    adapter: BaseQuickAdapter<T, BaseViewHolder>
): MutableList<T> {
    this.setEnableLoadMore(list.size >= pageSize)
    val newList = mutableListOf<T>().apply { addAll(adapter.data) }
    when (pageNum) {
        1 -> {

            this.finishRefresh()
            adapter.setList(list)
            return list
        }
        else -> {
            this.finishLoadMore()
            list?.let {
                it.let { itList -> newList.addAll(itList) }
            }
        }
    }
    adapter.setList(newList)
    return newList
}