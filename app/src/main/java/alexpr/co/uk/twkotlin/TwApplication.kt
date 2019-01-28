package alexpr.co.uk.twkotlin

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid

class TwApplication : Application() {

    companion object {
        val twService by lazy {
            TwService.create()
        }
    }

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this);
    }
}