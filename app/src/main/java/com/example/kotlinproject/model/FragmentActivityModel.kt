package com.example.kotlinproject.model

import com.example.kotlinproject.base.IBaseModel
import com.example.kotlinproject.base.`interface`.MyCallback
import com.example.kotlinproject.utils.LogUtil

/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject.model
 * @ClassName:      MainModel
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/10/9 10:14
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/10/9 10:14
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
open class FragmentActivityModel(callback: FragmentActivityCallback) : IBaseModel<MyCallback>() {
    private var back: FragmentActivityCallback = callback

    fun show1() {
        LogUtil.info("xxxx", "show1")

        Thread.sleep(10000)
        back.method1()
    }
}