package com.example.kotlinproject.presenter

import com.example.kotlinproject.base.BasePresenter
import com.example.kotlinproject.base.IBaseModel
import com.example.kotlinproject.base.IBaseView
import com.example.kotlinproject.base.`interface`.MyCallback
import com.example.kotlinproject.model.MainCallback
import com.example.kotlinproject.model.MainModel
import com.example.kotlinproject.view.MainView
import okhttp3.Call
import okhttp3.Callback
import java.io.IOException

/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject.presenter
 * @ClassName:      MainPresenter
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/10/9 9:50
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/10/9 9:50
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class MainPresenter(mainView: MainView) : BasePresenter<IBaseView>(mainView), MainCallback{



    fun work() {
        (modelLayer as MainModel).show1()
    }






    override fun getModel(): IBaseModel<MyCallback> {
        return MainModel(this)
    }

    override fun method1() {

    }

    override fun back() {

    }


}