package com.example.kotlinproject.net.retrofit


import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 *
 * @ProjectName:    KotlinProject
 * @Package:        com.example.kotlinproject.net.retrofit
 * @ClassName:      RetrofitUtil
 * @Description:     java类作用描述
 * @Author:         zhanghong
 * @CreateDate:     2020/9/30 17:14
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/9/30 17:14
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class RetrofitUtil {
    private lateinit var retrofit: Retrofit

    init {
        val logInterceptor = HttpLoggingInterceptor(HttpLogger())
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val myOkHttpClient = OkHttpClient().newBuilder()
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS).build()
        retrofit = Retrofit.Builder().baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(myOkHttpClient)
            .build()
    }


    /**
     * 双重校验单利
     */
    companion object {
        val instance: RetrofitUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitUtil()
        }
    }

    //直接获得RetrofitInterface
    fun getRetrofitInterface(): RetrofitInterface {
        return retrofit.create(
            RetrofitInterface::class.java
        )
    }


    //获得Retrofit
    fun getRetrofit(): Retrofit {
        return retrofit
    }
    //自定义拦截器/上传公共参数
    class LoggingInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            //请求前--打印请求信息
            val t1 = System.nanoTime()
            Log.e(
                "myMessage", String.format(
                    "Sending url %s \n %s \n\n%s \n\n %s",
                    request.url(), chain.connection(), request.headers(), request.body()
                )
            )

            //网络请求
            val response = chain.proceed(request)

            //网络响应后--打印响应信息
            val t2 = System.nanoTime()
            Log.e(
                "myMessage", String.format(
                    "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6, response.headers()
                )
            )
            return response

//            上传公共参数
//            Request request = chain.request();
//            Response response = null;
//            Request requestProcess = null ;
//            if("GET".equals(request.method())){
//                String url =  request.url().toString() + "&source=android";
//                Request.Builder builder =  request.newBuilder() ;
//                builder.get().url(url);
//                requestProcess =  builder.build();
//                response = chain.proceed(requestProcess);
//            } else {
//                FormBody.Builder builder = new FormBody.Builder() ;
//                RequestBody requestBody =  request.body() ;
//                if(requestBody instanceof FormBody){
//                    FormBody formBody = (FormBody)requestBody ;
//                    for (int i=0;i<formBody.size();i++){
//                        builder.add(formBody.encodedName(i),formBody.encodedValue(i));
//                    }
//                    builder.add("source","android");
//                }
//                requestProcess =  request.newBuilder().url(request.url().toString()).post(builder.build()).build() ;
//                response = chain.proceed(requestProcess);
//            }
//            return response;
        }
    }

    //日志信息
    class HttpLogger : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.e("myMessage", message)
        }
    }

}