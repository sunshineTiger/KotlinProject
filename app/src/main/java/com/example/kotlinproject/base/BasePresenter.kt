package com.example.kotlinproject.base

import com.example.kotlinproject.base.`interface`.MyCallback

/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject.base
 * @ClassName:      IBasePresenter
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/9/30 18:35
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/9/30 18:35
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
abstract class BasePresenter<T : IBaseView>(view: T) {

    protected var viewLayer: T? = view
    protected lateinit var modelLayer: IBaseModel<MyCallback>

    init {
        initModel()
    }

    private fun initModel() {
        modelLayer = bindModel()
    }

    /**
     * 获取model层
     */
    abstract fun bindModel(): IBaseModel<MyCallback>


    /**
     * 解绑view
     */
    fun detachView() {

        viewLayer = null
    }

}