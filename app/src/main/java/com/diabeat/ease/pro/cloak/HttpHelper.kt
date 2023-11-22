package com.diabeat.ease.pro.cloak

import com.diabeat.ease.pro.constant.logE
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

object HttpHelper {
    const val DEFAULT_CONNECT_TIME = 10L
    const val DEFAULT_WRITE_TIME = 30L
    const val DEFAULT_READ_TIME = 30L
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(DEFAULT_CONNECT_TIME, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_WRITE_TIME, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_READ_TIME, TimeUnit.SECONDS)
            .build()
    }

    fun sendRequestGet(
        baseUrl: String ,
        requestString: String,
        resultSuccess: (String) -> Unit,
        resultFailed: (Int, String) -> Unit
    ) {

        runCatching {
            val url = "${baseUrl}?${requestString}"
            "HttpHelper ${baseUrl} GET url = $url".logE()
            val request = Request.Builder().url(url)?.build()
            okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    "HttpHelper ${baseUrl} GET Failed code = 500  message = ${e?.message}".logE()
                    resultFailed.invoke(500, e?.message ?: "")
                }

                override fun onResponse(call: Call?, it: Response?) {
                    if (it?.isSuccessful == true) {
                        it.body().string().let { message ->
                            "HttpHelper ${baseUrl} GET Success code = ${it.code()}  message = ${message}".logE()
                            resultSuccess.invoke(message)
                        }
                        it.body().close()
                    } else {
                        "HttpHelper ${baseUrl} GET Failed code = ${it?.code()}  message = ${it?.message()}".logE()
                        resultFailed.invoke(it?.code() ?: 500, it?.message() ?: "")
                    }
                }

            })
        }

    }
}