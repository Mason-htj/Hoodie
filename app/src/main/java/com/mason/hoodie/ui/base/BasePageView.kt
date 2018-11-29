package com.mason.hoodie.ui.base

import com.mason.hoodie.ui.main.MainActivity

/**
 * Created by mason-hong on 29/11/2018.
 */
abstract class BasePageView(activity: MainActivity) : BaseView(activity) {
    abstract fun getTitle(): String
}