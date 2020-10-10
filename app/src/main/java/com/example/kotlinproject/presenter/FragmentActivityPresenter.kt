package com.example.kotlinproject.presenter

import com.example.kotlinproject.base.BasePresenter
import com.example.kotlinproject.base.IBaseModel
import com.example.kotlinproject.base.IBaseView
import com.example.kotlinproject.base.`interface`.MyCallback
import com.example.kotlinproject.model.FragmentActivityCallback
import com.example.kotlinproject.model.FragmentActivityModel
import com.example.kotlinproject.view.FragmentActivityView

/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject.presenter
 * @ClassName:      FragmentActivityPresenter
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/10/10 11:15
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/10/10 11:15
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class FragmentActivityPresenter(mainView: FragmentActivityView) :
    BasePresenter<IBaseView>(mainView), FragmentActivityCallback {
    override fun bindModel(): IBaseModel<MyCallback> {
        return FragmentActivityModel(this)
    }

    override fun method1() {
        (modelLayer as FragmentActivityModel).show1()
    }

    override fun back() {
        (viewLayer as FragmentActivityView).method2()
    }
}