package com.example.kotlinproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import com.example.kotlinproject.R

/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject.ui
 * @ClassName:      SimpleFragment
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/9/29 15:36
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/9/29 15:36
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class SimpleFragment : Fragment() {
    private lateinit var mTvTitle: TextView
    private lateinit var mFakeStatusBar: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragement_simple, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTvTitle = view.findViewById(R.id.tv_title)
        mFakeStatusBar = view.findViewById<View>(R.id.statusbarutil_fake_status_bar_view)
    }

    fun setTvTitleBackgroundColor(@ColorInt color: Int) {
        mTvTitle.setBackgroundColor(color)
        mFakeStatusBar.setBackgroundColor(color)
    }

}