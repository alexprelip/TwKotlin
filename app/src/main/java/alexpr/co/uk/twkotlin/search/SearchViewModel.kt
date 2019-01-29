package alexpr.co.uk.twkotlin.search

import alexpr.co.uk.twkotlin.TwService
import alexpr.co.uk.twkotlin.models.PlaceModel
import alexpr.co.uk.twkotlin.utils.QueryBuilder
import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.joda.time.DateTime

class SearchViewModel(val twService: TwService) : ViewModel() {
    val searchResult: MutableLiveData<List<PlaceModel>> = MutableLiveData()
    var filter: FilterModel
    val filterLiveData: MutableLiveData<FilterModel> = MutableLiveData()

    init {
        //the filter can be initialised from storage/location provider
        filter = FilterModel("some-salon", "NW3", 1, 3, DateTime(), null, "", "", "", false, false)
        filterLiveData.postValue(filter)
    }

    @SuppressLint("CheckResult")
    fun search() {
        twService.getPlaces(
                QueryBuilder(
                        treatment = filter.query,
                        hourStart = filter.startHour,
                        hourEnd = filter.endHour,
                        dayStart = filter.dateStart
                ).buildQuery()
        ).subscribe({ places -> searchResult.postValue(places) }, { throwable: Throwable -> throwable.printStackTrace() })
    }

    fun updateDateTimeFilter(hourStart: Int?, hourEnd: Int?, startDate: DateTime?, endDate: DateTime?) {
        filter.startHour = hourStart
        filter.endHour = hourEnd
        filter.dateStart = startDate
        filter.dateEnd = endDate
        filterLiveData.postValue(filter)
        search()
    }

    fun updateSearchQuery(query:String) {
        filter.query = query
        filterLiveData.postValue(filter)
        search()
    }
}