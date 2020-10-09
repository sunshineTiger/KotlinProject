package com.example.kotlinproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinproject.R


/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject.ui
 * @ClassName:      ImageFragment
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/9/29 15:17
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/9/29 15:17
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class ImageFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragement_image, container, false)
    }

}