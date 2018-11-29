package com.mason.hoodie.common

import com.mason.hoodie.ui.BaseView
import org.koin.android.ext.android.inject

/**
 * Created by mason-hong on 29/11/2018.
 */
inline fun <reified T : Any> BaseView.inject() = activity.inject<T>()