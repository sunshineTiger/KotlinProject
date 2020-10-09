package com.example.kotlinproject.model

import com.example.kotlinproject.base.IBaseModel
import com.example.kotlinproject.base.`interface`.MyCallback

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
open class MainModel(callback: MainCallback) : IBaseModel<MyCallback>() {
    private var back: MainCallback = callback

    fun show1() {
        back.method1()
    }
}