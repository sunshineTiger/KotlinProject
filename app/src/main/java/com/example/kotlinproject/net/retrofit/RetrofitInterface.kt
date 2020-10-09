package com.example.kotlinproject.net.retrofit

import com.example.kotlinproject.data.JavaBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject.net.retrofit
 * @ClassName:      RetrofitInterface
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/9/30 17:08
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/9/30 17:08
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
interface RetrofitInterface {
    @GET("nba/?key=71e58b5b2f930eaf1f937407acde08fe&")
    fun getCall(@Query("num") num: Int): Observable<JavaBean>
}