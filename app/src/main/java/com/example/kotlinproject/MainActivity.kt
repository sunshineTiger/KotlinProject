package com.example.kotlinproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.kotlinproject.base.BaseActivity
import com.example.kotlinproject.base.BasePresenter
import com.example.kotlinproject.base.IBaseModel
import com.example.kotlinproject.base.IBaseView
import com.example.kotlinproject.data.JavaBean
import com.example.kotlinproject.model.MainModel
import com.example.kotlinproject.net.retrofit.RetrofitUtil
import com.example.kotlinproject.presenter.MainPresenter
import com.example.kotlinproject.utils.IntentUtils
import com.example.kotlinproject.utils.LogUtil
import com.example.kotlinproject.utils.StatusBarUtil
import com.example.kotlinproject.view.MainView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : BaseActivity<MainPresenter>(), MainView {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    @SuppressLint("CheckResult")
    override fun initView() {
        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener(View.OnClickListener {
            IntentUtils.JumpTo(FragmentActivity::class.java)
//            val intent = Intent(this, FragmentActivity::class.java)
//            startActivity(intent)
//            showLoading()
//            mPresenter!!.work()
//            hideLoading()
        })
//        val observable: Observable<JavaBean> =
//            RetrofitUtil.instance.getRetrofitInterface().getCall(1)
//        observable.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { t -> Log.e("getDataFromServer", t.getNewslist()!!.size.toString() + "") }
    }


    override fun setStatusBar() {
        StatusBarUtil.setTranslucent(this, 0)
    }

    override fun success() {
        LogUtil.info("xxxx", "success")
    }

    override fun showLoading() {
        super.showLoading()
        LogUtil.info("xxxx", "showLoading")
    }

    override fun hideLoading() {
        super.hideLoading()
        LogUtil.info("xxxx", "hideLoading")
    }

    override fun showToast(str: String) {
        super.showToast(str)
    }


    override fun bindPresenter(): MainPresenter {
        LogUtil.info("xxxx", "getPresenter")
        return MainPresenter(this)
    }

    override fun initData() {

    }
}