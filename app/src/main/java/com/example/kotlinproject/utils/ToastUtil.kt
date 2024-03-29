package com.example.kotlinproject.utils

import android.widget.Toast
import com.example.kotlinproject.base.AppManager

/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject.utils
 * @ClassName:      ToastUtil
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/10/9 16:35
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/10/9 16:35
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class ToastUtil {

    companion object {
        fun show(str: String) {
            Toast.makeText(
                AppManager.instance.currentActivity().applicationContext,
                str,
                Toast.LENGTH_SHORT
            ).show()
        }

        fun showLong(){
            Toast.makeText(
                    AppManager.instance.currentActivity().applicationContext,
                    str,
                    Toast.LENGTH_SHORT
            ).show()
        }
    }
}