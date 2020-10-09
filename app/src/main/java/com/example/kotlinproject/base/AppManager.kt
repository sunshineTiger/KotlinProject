package com.example.kotlinproject.base

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.annotations.NotNull
import java.util.*
import kotlin.system.exitProcess

/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject.base
 * @ClassName:      AppManager
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/9/28 10:53
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/9/28 10:53
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class AppManager private constructor() {

    private var activityStack: Stack<AppCompatActivity>

    init {
        activityStack = Stack()
    }

    /**
     * 双重校验单利
     */
    companion object {
        val instance: AppManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AppManager()
        }


    }

    fun isDestroy(mActivity: Activity): Boolean {
        if (mActivity == null || mActivity.isFinishing || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && mActivity.isDestroyed)) {
            return true
        }
        return false
    }

    /**
     * 添加Activity到栈
     */
    fun addActivity(@NotNull activity: AppCompatActivity) {
        activityStack = Stack()
        if (activityStack.size > 0) {
            val existInStack = isExistInStack(activity)
            val current = isCurrent(activity)
            if (existInStack && !current) {
                activityStack.remove(activity)
            }
        }
        activityStack.push(activity)
    }

    fun getStack(): Stack<AppCompatActivity> {
        return activityStack
    }


    /**
     * 从堆栈移除指定的Activity
     */
    fun removeActivity(@NotNull activity: AppCompatActivity) {
        activityStack.remove(activity)
        activity
    }

    private fun isExistInStack(activity: AppCompatActivity): Boolean {
        for (s in activityStack) {
            if (s.javaClass.simpleName == activity.javaClass.simpleName) {
                return true
            }
        }
        return false
    }

    private fun isCurrent(cls: AppCompatActivity): Boolean {
        var appCompatActivity: AppCompatActivity = currentActivity()
        return appCompatActivity.javaClass.simpleName == cls.javaClass.simpleName
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        finishActivity(activityStack.lastElement())
    }

    fun finishActivity(activity: AppCompatActivity) {
        activityStack.remove(activity)
        activity.finish()
    }

    /**
     * 判断指定类名的Activity是否存在与栈中
     */
    fun isExistInStack(cls: Class<*>): Boolean {
        for (activity in activityStack) {
            if (activity.javaClass == cls) {
                return true
            }
        }
        return false
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack.size
        while (i < size) {
            if (null != activityStack[i]) {
                activityStack[i].finish()
            }
            i++
        }
        activityStack.clear()
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
     fun currentActivity(): AppCompatActivity {
        return if (activityStack.size > 0) {
            activityStack[activityStack.size - 1]
        } else {
            activityStack.lastElement()
        }
    }

    /**
     * 退出应用程序
     */
    fun onAppExit(context: Context) {
        try {
            finishAllActivity()
            val activityMgr = context
                .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityMgr.restartPackage(context.packageName)
            exitProcess(0)
        } catch (e: Exception) {
        }
    }
}