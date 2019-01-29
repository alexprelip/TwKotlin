package alexpr.co.uk.twkotlin.utils

import org.joda.time.DateTime

class QueryBuilder(val treatmentGroup: String = "", val treatment: String = "", val hourStart: Int?, val hourEnd: Int?, val dayStart: DateTime? = null) {
    init {
        if (treatment.isNotEmpty() && treatmentGroup.isNotEmpty()) throw Exception("treatment and treatmentGroup cannot be assigned at the same time")
        if (hourStart == null && hourEnd != null) throw Exception("Both hour intervals must be set")
        if (hourStart != null && hourEnd == null) throw Exception("Both hour intervals must be set")
    }

    fun buildQuery(): String {
         return if (treatment.isEmpty()) "" else {"treatment-${ParseUrlUtil.parseUrlForItem(treatment)}/"} +
         if (treatmentGroup.isEmpty()) "" else {"treatment-group-$treatmentGroup/"} +
         if (hourStart == null) "" else {"time-range-${String.format("%02d00", hourStart)}-to-${String.format("%02d00", hourEnd)}/"} +
         if (dayStart == null) "" else {"available-on-${dayStart.dayOfMonth().asText}-${dayStart.monthOfYear().asText}-${dayStart.year().asText}/"}
    }
}