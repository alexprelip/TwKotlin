package alexpr.co.uk.twkotlin

import android.app.Application

class TwApplication : Application() {

    companion object {
        val twService by lazy {
            TwService.create()
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}