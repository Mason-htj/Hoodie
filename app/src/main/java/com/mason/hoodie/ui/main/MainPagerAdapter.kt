package com.mason.hoodie.ui.main

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.mason.hoodie.ui.base.BasePageView
import com.mason.hoodie.ui.base.BaseView

/**
 * Created by mason-hong on 29/11/2018.
 */
class MainPagerAdapter(private val views: List<BasePageView>) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return views[position].apply {
            container.addView(this.container)
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        views[position].apply {
            container.removeView(this.container)
        }
    }

    override fun isViewFromObject(view: View, item: Any): Boolean = item is BaseView && item.container == view

    override fun getCount(): Int = views.size

    override fun getPageTitle(position: Int): CharSequence? = views[position].getTitle()
}