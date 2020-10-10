package com.example.kotlinproject

import android.app.Application
import com.example.kotlinproject.utils.LogUtil


/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject
 * @ClassName:      MyApplication
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/10/10 10:57
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/10/10 10:57
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */

class MyApplication : Application() {


    companion object {
        private var debug: Boolean = false

    }


    override fun onCreate() {
        super.onCreate()
        debug = true
        LogUtil.init(debug)

    }


}