package pl.gregnote.testyourowncv.api

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.gregnote.testyourowncv.BuildConfig
import pl.gregnote.testyourowncv.models.Details
import pl.gregnote.testyourowncv.models.Item
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServiceInterface {

    @GET("list")
    fun getList(): Observable<ArrayList<Item>>

    @GET("")
    fun getDetails(@Url fieldName: String): Observable<Details>

    companion object Factory {

        fun create(): ApiServiceInterface {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(
                if (BuildConfig.BUILD_TYPE.equals("debug", true))
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            )

            val httpClientBuilder = OkHttpClient.Builder()
            httpClientBuilder.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://gist.githubusercontent.com/GregNote/8284740e952ebec5fe2f1bca4f968d4f/raw/fe371cdbe2eb5135a6c51b635201b0c862ba7821/")
                .build()
            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}
