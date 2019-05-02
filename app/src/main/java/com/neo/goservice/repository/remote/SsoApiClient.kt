package com.neo.goservice.repository.remote

import com.neo.goservice.repository.remote.RemoteAPI.Companion.getOkHttpClient
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class SsoApiClient {
    companion object {
        private val TAG = SsoApiClient::class.simpleName
        private var sInstance: SsoApiClient? = null
        private lateinit var mService: SsoApiClientService

        //一頁的listView顯示幾個，之後應由後台做設定
        private const val LISTVIEW_PAGECOUNT = 10

        fun getInstance(): SsoApiClient? {
            if (sInstance == null) {
                synchronized(SsoApiClient::class) {
                    if (sInstance == null) {
                        sInstance = SsoApiClient()
                    }
                }
            }
            return sInstance
        }
    }

    init {
        val url = "https://login.fusheng.com/"
        val client = getOkHttpClient()

        val retrofit = Retrofit.Builder()
            .baseUrl(HttpUrl.get(url))
            .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        mService = retrofit.create(SsoApiClientService::class.java)
    }


    /*--------------------------------------------------------------------------------------------*/
    /* APIs */

    fun login(email: String, password: String, address: String = "127.0.0.1"): Single<ResponseBody> {
        val body: RequestBody = RequestBody.create(
            MediaType.parse("application/xml"),
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sso=\"http://sso.fusheng.com/\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <sso:SingleSignOnWS>\n" +
                    "         <!--Optional:-->\n" +
                    "         <applicationId>0</applicationId>\n" +
                    "         <!--Optional:-->\n" +
                    "         <companyId>null</companyId>\n" +
                    "         <!--Optional:-->\n" +
                    "         <userId>" + email + "</userId>\n" +
                    "         <!--Optional:-->\n" +
                    "         <password>" + password + "</password>\n" +
                    "         <!--Optional:-->\n" +
                    "         <userIP>" + address + "</userIP>\n" +
                    "      </sso:SingleSignOnWS>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>"
        )

        return mService.login(body)
    }
}