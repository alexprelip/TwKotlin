package alexpr.co.uk.twkotlin.search

import org.joda.time.DateTime

data class FilterModel(
    var query: String,
    var location: String,
    var startHour: Int?,
    var endHour: Int?,
    var dateStart: DateTime?,
    var dateEnd: DateTime?,
    var sortType: String,
    var maxPrice: String,
    var minRating: String,
    var onlyOffPeak: Boolean,
    var onlyTopRated: Boolean
)