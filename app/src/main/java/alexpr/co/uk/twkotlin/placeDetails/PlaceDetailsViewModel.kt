package alexpr.co.uk.twkotlin.placeDetails

import alexpr.co.uk.twkotlin.TwService
import alexpr.co.uk.twkotlin.models.PlaceModel
import alexpr.co.uk.twkotlin.models.Section
import alexpr.co.uk.twkotlin.utils.ParseUrlUtil
import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaceDetailsViewModel(val twService: TwService) : ViewModel() {
    val placeServices: MutableLiveData<List<Section>> = MutableLiveData()
    val placeDetails: MutableLiveData<PlaceModel> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getPlaceDetails(placeName:String) {
        twService.getPlace(ParseUrlUtil.parseUrlForItem(placeName))
                .subscribe({ placeModel -> placeDetails.postValue(placeModel) }, { throwable: Throwable -> throwable.printStackTrace() })
    }

    @SuppressLint("CheckResult")
    fun getPlaceServices(placeName:String) {
        twService.getPlaceServices(ParseUrlUtil.parseUrlForItem(placeName))
                .subscribe({ sections -> placeServices.postValue(sections) }, { throwable: Throwable -> throwable.printStackTrace() })
    }
}