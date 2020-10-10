package com.example.kotlinproject.utils

import android.util.Log

/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject.utils
 * @ClassName:      LogUtil
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/10/10 10:53
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/10/10 10:53
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class LogUtil {


    companion object {
        private var isDebug = false
        fun init(isDebugOpen: Boolean) {
            isDebug = isDebugOpen
        }

        fun info(tagName: String?, info: String?) {
            if (isDebug) {
                Log.i(tagName, info!!)
            }
        }

        fun error(tagName: String?, error: String?) {
            if (isDebug) {
                Log.e(tagName, error!!)
            }
        }

        fun debug(tagName: String?, debug: String?) {
            if (isDebug) {
                Log.d(tagName, debug!!)
            }
        }
    }

}