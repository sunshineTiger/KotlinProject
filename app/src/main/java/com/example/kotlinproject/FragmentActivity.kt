package com.example.kotlinproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.kotlinproject.base.BaseActivity
import com.example.kotlinproject.presenter.FragmentActivityPresenter
import com.example.kotlinproject.ui.ImageFragment
import com.example.kotlinproject.ui.SimpleFragment
import com.example.kotlinproject.view.FragmentActivityView
import java.util.*

/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject
 * @ClassName:      FragmentActivity
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/9/29 16:11
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/9/29 16:11
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */

class FragmentActivity : BaseActivity<FragmentActivityPresenter>(), FragmentActivityView {
    private lateinit var mVpHome: ViewPager
    private lateinit var mFragmentList: MutableList<Fragment>
    override fun getLayoutId(): Int {
        return R.layout.fragmentview
    }

    override fun setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null)
    }

    override fun initView() {
        mVpHome = findViewById(R.id.vp_home)
        mFragmentList = mutableListOf()
        mFragmentList.add(ImageFragment())
        mFragmentList.add(SimpleFragment())
        mFragmentList.add(ImageFragment())
        mFragmentList.add(SimpleFragment())
        mVpHome.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                    }
                    else -> {
                        val random = Random()
                        val color = -0x1000000 or random.nextInt(0xffffff)
                        if (mFragmentList[position] is SimpleFragment) {
                            (mFragmentList[position] as SimpleFragment).setTvTitleBackgroundColor(
                                color
                            )
                        }
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        mVpHome.setAdapter(object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return mFragmentList[position]
            }

            override fun getCount(): Int {
                return mFragmentList.size
            }


        })
    }

    override fun initData() {

    }

    override fun bindPresenter(): FragmentActivityPresenter {
        return FragmentActivityPresenter(this)
    }

    override fun method2() {

    }

}