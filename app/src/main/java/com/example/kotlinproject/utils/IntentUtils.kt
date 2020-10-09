package com.example.kotlinproject.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinproject.base.AppManager
import java.io.Serializable

/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject.utils
 * @ClassName:      IntentUtils
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/10/9 16:32
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/10/9 16:32
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class IntentUtils {

    /**
     * 打电话
     *
     * @param phoneNum
     */
    var activity: Activity = AppManager.instance.currentActivity()

    fun call(phoneNum: String) {
        val intent = Intent()
        // 启动电话程序
        intent.action = Intent.ACTION_CALL
        intent.data = Uri.parse("tel:$phoneNum")
        activity.startActivity(intent)
    }

    //    public static void JumpFinishTo(Class<?> resultactivity) {
    //        Intent intent = new Intent( activity, resultactivity);
    //        activity.startActivity(intent);
    ////        MyLog.e("finishActivity", AppManager.getAppManager().currentActivity().getCallingPackage());
    //        AppManager.getAppManager().finishActivity(activity);
    //    }
    fun JumpFinishTo(
        activity: AppCompatActivity,
        resultactivity: Class<*>?
    ) {
        val intent = Intent(activity, resultactivity)
        //        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.startActivity(intent)
        //        MyLog.e("finishActivity", AppManager.getAppManager().currentActivity().getCallingPackage());
        AppManager.instance.finishActivity(activity)
        activity.overridePendingTransition(0, 0)
    }

    fun JumpFinishTo(resultactivity: Class<*>?) {
        val intent = Intent(activity, resultactivity)
        //        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.startActivity(intent)
        //        MyLog.e("finishActivity", AppManager.getAppManager().currentActivity().getCallingPackage());
        AppManager.instance.finishActivity()
        activity.overridePendingTransition(0, 0)
    }

    fun JumpFinishToHaveOneBoolean(
        activity: AppCompatActivity,
        resultactivity: Class<*>?,
        key: String?,
        value: Boolean
    ) {
        val state = getNetWorkState(activity)
        if (state == NETWORK_NONE) {
            ToastUtil.show("网络异常")
        }
        val intent = Intent(activity, resultactivity)
        val bundle = Bundle()
        bundle.putBoolean(key, value)
        intent.putExtras(bundle)
        activity.startActivity(intent)
        AppManager.instance.finishActivity(activity)
        activity.overridePendingTransition(0, 0)
    }

    fun JumpToHaveOneBoolean(
        activity: AppCompatActivity,
        resultactivity: Class<*>?,
        key: String?,
        value: Boolean
    ) {
        val state = getNetWorkState(activity)
        if (state == NETWORK_NONE) {
            ToastUtil.show("网络异常")
        }
        val intent = Intent(activity, resultactivity)
        val bundle = Bundle()
        bundle.putBoolean(key, value)
        intent.putExtras(bundle)
        activity.startActivity(intent)
        activity.overridePendingTransition(0, 0)
    }

    fun JumpTo(act: Activity, resultactivity: Class<*>?) {
        val state = getNetWorkState(activity)
        if (state == NETWORK_NONE) {
            ToastUtil.show("网络异常")
        }
        val intent = Intent(act, resultactivity)
        act.startActivity(intent)
    }

    fun JumpTo(resultactivity: Class<*>?) {
        val state = getNetWorkState(activity)
        if (state == NETWORK_NONE) {
            ToastUtil.show("网络异常")
        }
        val intent = Intent(activity, resultactivity)
        activity.startActivity(intent)
        activity.overridePendingTransition(0, 0)
    }

    fun JumpToHaveOne(
        resultactivity: Class<*>?,
        key: String?,
        value: String?
    ) {
        val state = getNetWorkState(activity)
        if (state == NETWORK_NONE) {
            ToastUtil.show("网络异常")
        }
        val intent = Intent(activity, resultactivity)
        val bundle = Bundle()
        bundle.putString(key, value)
        intent.putExtras(bundle)
        activity.startActivity(intent)
        activity.overridePendingTransition(0, 0)
    }

    fun JumpToHaveOneInt(
        resultactivity: Class<*>?,
        key: String?,
        value: Int
    ) {
        val state = getNetWorkState(activity)
        if (state == NETWORK_NONE) {
            ToastUtil.show("网络异常")
        }
        val intent = Intent(activity, resultactivity)
        val bundle = Bundle()
        bundle.putInt(key, value)
        intent.putExtras(bundle)
        activity.startActivity(intent)
        activity.overridePendingTransition(0, 0)
    }

    fun JumpToHaveTwo(
        resultactivity: Class<*>?,
        key1: String?,
        value1: String?,
        key2: String?,
        value2: String?
    ) {
        val state = getNetWorkState(activity)
        if (state == NETWORK_NONE) {
            ToastUtil.show("网络异常")
        }
        val intent = Intent(activity, resultactivity)
        val bundle = Bundle()
        bundle.putString(key1, value1)
        bundle.putString(key2, value2)
        intent.putExtras(bundle)
        activity.startActivity(intent)
        activity.overridePendingTransition(0, 0)
    }

    fun JumpToHaveStringArray(
        resultactivity: Class<*>?,
        key1: String?,
        value: Array<String?>?
    ) {
        val state = getNetWorkState(activity)
        if (state == NETWORK_NONE) {
            ToastUtil.show("网络异常")
        }
        val intent = Intent(activity, resultactivity)
        val bundle = Bundle()
        bundle.putStringArray(key1, value)
        intent.putExtras(bundle)
        activity.startActivity(intent)
        activity.overridePendingTransition(0, 0)
    }

    fun JumpToHaveObj(
        resultactivity: Class<*>?,
        key: String?,
        value: Serializable?
    ) {
        val state = getNetWorkState(activity)
        if (state == NETWORK_NONE) {
            ToastUtil.show("网络异常")
        }
        val intent = Intent(activity, resultactivity)
        val bundle = Bundle()
        bundle.putSerializable(key, value)
        intent.putExtras(bundle)
        activity.startActivity(intent)
        activity.overridePendingTransition(0, 0)
    }

    fun JumpToHaveObjAndStr(
        resultactivity: Class<*>?,
        key: String?,
        value: Serializable?,
        key1: String?,
        value1: String?
    ) {
        val state = getNetWorkState(activity)
        if (state == NETWORK_NONE) {
            ToastUtil.show("网络异常")
        }
        val intent = Intent(activity, resultactivity)
        val bundle = Bundle()
        bundle.putSerializable(key, value)
        bundle.putString(key1, value1)
        intent.putExtras(bundle)
        activity.startActivity(intent)
        activity.overridePendingTransition(0, 0)
    }
//    public static  void  JumpGoH5(String tital,String net_url)
//    {
//        Intent intent = new Intent();
//        intent.setClass(activity, GoH5Activity.class);
//
//        Bundle mBundle = new Bundle();
//        mBundle.putString(AppAllKey.GOH5_TITAL_KEY, tital);
//        mBundle.putString(AppAllKey.GOH5_ARTICAL_KEY, net_url);
//        intent.putExtras(mBundle);
//        activity.startActivity(intent);
//    }

    //    public static  void  JumpGoH5(String tital,String net_url)
    //    {
    //        Intent intent = new Intent();
    //        intent.setClass(activity, GoH5Activity.class);
    //
    //        Bundle mBundle = new Bundle();
    //        mBundle.putString(AppAllKey.GOH5_TITAL_KEY, tital);
    //        mBundle.putString(AppAllKey.GOH5_ARTICAL_KEY, net_url);
    //        intent.putExtras(mBundle);
    //        activity.startActivity(intent);
    //    }
    fun jumpToBrowser(url: String?) {
        val state = getNetWorkState(activity)
        if (state == NETWORK_NONE) {
            ToastUtil.show("网络异常")
        }
        val intent = Intent()
        intent.action = "android.intent.action.VIEW"
        val content_url = Uri.parse(url)
        intent.data = content_url
        activity.startActivity(intent)
        activity.overridePendingTransition(0, 0)
    }

    /**
     * 没有连接网络
     */
    val NETWORK_NONE = -1

    /**
     * 移动网络
     */
    val NETWORK_MOBILE = 0

    /**
     * 无线网络
     */
    val NETWORK_WIFI = 1

    fun getNetWorkState(context: Context): Int {
        // 得到连接管理器对象
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                return NETWORK_WIFI
            } else if (activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                return NETWORK_MOBILE
            }
        } else {
            return NETWORK_NONE
        }
        return NETWORK_NONE
    }
}