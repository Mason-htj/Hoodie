package com.mason.hoodie.ui.base

import android.arch.lifecycle.*
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.mason.hoodie.ui.main.MainActivity

/**
 * Created by mason-hong on 29/11/2018.
 */
abstract class BaseView(val activity: MainActivity) :
    LifecycleOwner,
    LifecycleObserver,
    View.OnAttachStateChangeListener {

    val container = FrameLayout(activity)
    var contentView: View? = null
        private set
    private val lifecycleRegistry = LifecycleRegistry(this)

    init {
        activity.lifecycle.addObserver(this)
        container.addOnAttachStateChangeListener(this)
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected open fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected open fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected open fun onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected open fun onDestroy() {
    }

    protected fun <T : ViewDataBinding> bindingView(id: Int) = DataBindingUtil.inflate<T>(
        LayoutInflater.from(activity),
        id,
        container,
        true
    ).also { contentView = it.root }!!

    override fun onViewDetachedFromWindow(v: View?) {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    override fun onViewAttachedToWindow(v: View?) {
        lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }
}