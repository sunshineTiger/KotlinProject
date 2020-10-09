package com.example.kotlinproject.utils


import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import com.example.kotlinproject.R
import org.jetbrains.annotations.NotNull

/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject.utils
 * @ClassName:      StatusBarUtil
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/9/29 14:10
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/9/29 14:10
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class StatusBarUtil {

    companion object {
        val DEFAULT_STATUS_BAR_ALPHA = 112
        private val FAKE_STATUS_BAR_VIEW_ID: Int =
            R.id.statusbarutil_fake_status_bar_view
        private val FAKE_TRANSLUCENT_VIEW_ID: Int =
            R.id.statusbarutil_translucent_view
        private val TAG_KEY_HAVE_SET_OFFSET = -123


        /**
         * 设置状态栏颜色
         *
         * @param activity 需要设置的 activity
         * @param color    状态栏颜色值
         */
        fun setColor(@NotNull activity: Activity, @ColorInt color: Int) {
            setColor(activity, color, DEFAULT_STATUS_BAR_ALPHA)
        }

        /**
         * 设置状态栏颜色
         *
         * @param activity       需要设置的activity
         * @param color          状态栏颜色值
         * @param statusBarAlpha 状态栏透明度
         */
        fun setColor(
            activity: Activity,
            @ColorInt color: Int,
            @IntRange(from = 0, to = 255) statusBarAlpha: Int
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                activity.window.statusBarColor = calculateStatusColor(color, statusBarAlpha)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                val decorView = activity.window.decorView as ViewGroup
                val fakeStatusBarView = decorView.findViewById<View>(FAKE_STATUS_BAR_VIEW_ID)
                if (fakeStatusBarView != null) {
                    if (fakeStatusBarView.visibility == View.GONE) {
                        fakeStatusBarView.visibility = View.VISIBLE
                    }
                    fakeStatusBarView.setBackgroundColor(
                        calculateStatusColor(
                            color,
                            statusBarAlpha
                        )
                    )
                } else {
                    decorView.addView(
                        createStatusBarView(
                            activity,
                            color,
                            statusBarAlpha
                        )
                    )
                }
                setRootView(activity)
            }
        }

        /**
         * 设置根布局参数
         */
        private fun setRootView(activity: Activity) {
            val parent =
                activity.findViewById<View>(android.R.id.content) as ViewGroup
            var i = 0
            val count = parent.childCount
            while (i < count) {
                val childView = parent.getChildAt(i)
                if (childView is ViewGroup) {
                    childView.setFitsSystemWindows(true)
                    childView.clipToPadding = true
                }
                i++
            }
        }


        /**
         * 生成一个和状态栏大小相同的半透明矩形条
         *
         * @param activity 需要设置的activity
         * @param color    状态栏颜色值
         * @param alpha    透明值
         * @return 状态栏矩形条
         */
        private fun createStatusBarView(
            activity: Activity,
            @ColorInt color: Int,
            alpha: Int
        ): View? {
            // 绘制一个和状态栏一样高的矩形
            val statusBarView = View(activity)
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(activity)
            )
            statusBarView.layoutParams = params
            statusBarView.setBackgroundColor(calculateStatusColor(color, alpha))
            statusBarView.id = FAKE_STATUS_BAR_VIEW_ID
            return statusBarView
        }


        /**
         * 获取状态栏高度
         *
         * @param context context
         * @return 状态栏高度
         */
        private fun getStatusBarHeight(context: Context): Int {
            // 获得状态栏高度
            val resourceId =
                context.resources.getIdentifier("status_bar_height", "dimen", "android")
            return context.resources.getDimensionPixelSize(resourceId)
        }

        /**
         * 计算状态栏颜色
         *
         * @param color color值
         * @param alpha alpha值
         * @return 最终的状态栏颜色
         */
        private fun calculateStatusColor(@ColorInt color: Int, alpha: Int): Int {
            if (alpha == 0) {
                return color
            }
            val a = 1 - alpha / 255f
            var red = color shr 16 and 0xff
            var green = color shr 8 and 0xff
            var blue = color and 0xff
            red = (red * a + 0.5).toInt()
            green = (green * a + 0.5).toInt()
            blue = (blue * a + 0.5).toInt()
            return 0xff shl 24 or (red shl 16) or (green shl 8) or blue
        }

        /**
         * 使状态栏半透明
         *
         *
         * 适用于图片作为背景的界面,此时需要图片填充到状态栏
         *
         * @param activity       需要设置的activity
         * @param statusBarAlpha 状态栏透明度
         */
        fun setTranslucent(
            activity: Activity,
            @IntRange(from = 0, to = 255) statusBarAlpha: Int
        ) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                return
            }
            setTransparent(activity)
            addTranslucentView(activity, statusBarAlpha)
        }

        /**
         * 添加半透明矩形条
         *
         * @param activity       需要设置的 activity
         * @param statusBarAlpha 透明值
         */
        private fun addTranslucentView(
            activity: Activity,
            @IntRange(from = 0, to = 255) statusBarAlpha: Int
        ) {
            val contentView = activity.findViewById<View>(android.R.id.content) as ViewGroup
            val fakeTranslucentView = contentView.findViewById<View>(FAKE_TRANSLUCENT_VIEW_ID)
            if (fakeTranslucentView != null) {
                if (fakeTranslucentView.visibility == View.GONE) {
                    fakeTranslucentView.visibility = View.VISIBLE
                }
                fakeTranslucentView.setBackgroundColor(
                    Color.argb(
                        statusBarAlpha, 0,
                        0,
                        0
                    )
                )
            } else {
                contentView.addView(
                    createTranslucentStatusBarView(
                        activity,
                        statusBarAlpha
                    )
                )
            }
        }

        /**
         * 设置状态栏全透明
         *
         * @param activity 需要设置的activity
         */
        fun setTransparent(activity: Activity) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                return
            }
            transparentStatusBar(activity)
            setRootView(activity)
        }

        /**
         * 创建半透明矩形 View
         *
         * @param alpha 透明值
         * @return 半透明 View
         */
        private fun createTranslucentStatusBarView(
            activity: Activity,
            alpha: Int
        ): View? {
            // 绘制一个和状态栏一样高的矩形
            val statusBarView = View(activity)
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(activity)
            )
            statusBarView.layoutParams = params
            statusBarView.setBackgroundColor(Color.argb(alpha, 0, 0, 0))
            statusBarView.id = FAKE_TRANSLUCENT_VIEW_ID
            return statusBarView
        }

        /**
         * 使状态栏透明
         */
        @TargetApi(Build.VERSION_CODES.KITKAT)
        private fun transparentStatusBar(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window
                    .addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                activity.window
                    .addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                activity.window.statusBarColor = Color.TRANSPARENT
            } else {
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
        }

        /**
         * 为 fragment 头部是 ImageView 的设置状态栏透明
         *
         * @param activity       fragment 对应的 activity
         * @param needOffsetView 需要向下偏移的 View
         */
        fun setTranslucentForImageViewInFragment(
            activity: Activity,
            needOffsetView: View?
        ) {
            setTranslucentForImageViewInFragment(
                activity,
                DEFAULT_STATUS_BAR_ALPHA,
                needOffsetView
            )
        }

        /**
         * 为 fragment 头部是 ImageView 的设置状态栏透明
         *
         * @param activity       fragment 对应的 activity
         * @param statusBarAlpha 状态栏透明度
         * @param needOffsetView 需要向下偏移的 View
         */
        fun setTranslucentForImageViewInFragment(
            activity: Activity, @IntRange(from = 0, to = 255) statusBarAlpha: Int,
            needOffsetView: View?
        ) {
            setTranslucentForImageView(activity, statusBarAlpha, needOffsetView)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                clearPreviousSetting(activity)
            }
        }

        ///////////////////////////////////////////////////////////////////////////////////
        @TargetApi(Build.VERSION_CODES.KITKAT)
        private fun clearPreviousSetting(activity: Activity) {
            val decorView = activity.window.decorView as ViewGroup
            val fakeStatusBarView =
                decorView.findViewById<View>(FAKE_STATUS_BAR_VIEW_ID)
            if (fakeStatusBarView != null) {
                decorView.removeView(fakeStatusBarView)
                val rootView =
                    (activity.findViewById<View>(R.id.content) as ViewGroup).getChildAt(
                        0
                    ) as ViewGroup
                rootView.setPadding(0, 0, 0, 0)
            }
        }


        /**
         * 为头部是 ImageView 的界面设置状态栏透明
         *
         * @param activity       需要设置的activity
         * @param statusBarAlpha 状态栏透明度
         * @param needOffsetView 需要向下偏移的 View
         */
        fun setTranslucentForImageView(
            activity: Activity, @IntRange(from = 0, to = 255) statusBarAlpha: Int,
            needOffsetView: View?
        ) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                return
            }
            setTransparentForWindow(activity)
            addTranslucentView(activity, statusBarAlpha)
            if (needOffsetView != null) {
                val haveSetOffset =
                    needOffsetView.getTag(TAG_KEY_HAVE_SET_OFFSET)
                if (haveSetOffset != null && haveSetOffset as Boolean) {
                    return
                }
                val layoutParams =
                    needOffsetView.layoutParams as MarginLayoutParams
                layoutParams.setMargins(
                    layoutParams.leftMargin,
                    layoutParams.topMargin + getStatusBarHeight(activity),
                    layoutParams.rightMargin,
                    layoutParams.bottomMargin
                )
                needOffsetView.setTag(TAG_KEY_HAVE_SET_OFFSET, true)
            }
        }

        /**
         * 设置透明
         */
        private fun setTransparentForWindow(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.statusBarColor = Color.TRANSPARENT
                activity.window
                    .decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                activity.window
                    .setFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    )
            }
        }
    }
}