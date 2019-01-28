package alexpr.co.uk.twkotlin

import alexpr.co.uk.twkotlin.models.MainMenu
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface TwService {

    companion object {
        fun create(): TwService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor(FakeInterceptor()).build())
                .baseUrl("http://a.a/")
                .build()

            return retrofit.create(TwService::class.java)
        }
    }

    @GET("home/menu")
    fun getHomeMenu(): Observable<MainMenu>

    @GET("places/{path}")
    fun getPlaces(@Path(value="path", encoded = true) path: String): Observable<MainMenu>
}
