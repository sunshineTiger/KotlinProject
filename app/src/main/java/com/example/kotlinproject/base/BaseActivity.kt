package com.example.kotlinproject.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinproject.R
import com.example.kotlinproject.utils.IntentUtils
import com.example.kotlinproject.utils.StatusBarUtil
import com.example.kotlinproject.utils.ToastUtil


/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject.base
 * @ClassName:      BaseActivity
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/9/28 10:48
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/9/28 10:48
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
abstract class BaseActivity<T : BasePresenter<IBaseView>> : AppCompatActivity(), IBaseView {

    lateinit var context: Context;
    open var mPresenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = applicationContext
        AppManager.instance.addActivity(this)
        initBeforeSetContentView()
        setContentView(getLayoutId())
        setStatusBar()
        mPresenter = getPresenter()
        initData()
        initView()
    }

    /**
     *在设置布局之前需要初始化操作
     */
    open fun initBeforeSetContentView() {

    }

    open fun setStatusBar() {
        StatusBarUtil.setColor(this, resources.getColor(R.color.colorPrimary))
    }

    /**
     * 获取当前界面布局ID
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化view
     */
    abstract fun initView()

    /**
     *显示加载框
     */
    open fun showLoading() {

    }

    /**
     * 隐藏加载框
     */
    open fun hideLoading() {

    }

    /**
     *吐司提示
     */
    open fun showToast(str: String) {
        ToastUtil.show(str)
    }


    /**
     * 初始化Presenter
     */
    abstract fun getPresenter(): T

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        mPresenter = null
    }


}