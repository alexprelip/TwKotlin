package alexpr.co.uk.twkotlin.home

import alexpr.co.uk.twkotlin.TwService
import alexpr.co.uk.twkotlin.models.MainMenu
import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.schedulers.Schedulers

class MainActViewModel(val service: TwService) : ViewModel() {
    var mainMenu: MutableLiveData<MainMenu> = MutableLiveData()

    init {
        getMainMenuData()
    }

    fun getHomeData(): LiveData<MainMenu> {
        return mainMenu
    }

    @SuppressLint("CheckResult")
    private fun getMainMenuData() {
        service.getHomeMenu().subscribeOn(Schedulers.io()).subscribe(
            { response: MainMenu -> mainMenu.postValue(response) },
            { t: Throwable -> Log.e("alexp", "request failed: " + t.message) })
    }
}