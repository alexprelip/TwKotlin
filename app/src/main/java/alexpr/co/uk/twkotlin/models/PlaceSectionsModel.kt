package alexpr.co.uk.twkotlin.models

import java.io.Serializable

data class Section(val name: String, val serviceItem: MutableList<ServiceItem>, var showOpen: Boolean)

data class ServiceItem(
        val serviceSubItem: MutableList<ServiceModel>,
        val serviceModel: ServiceModel,
        val details: String,
        val genericCategory: String,
        var showOpen: Boolean,
        val isSingleOption: Boolean)

data class ServiceModel(
        val serviceName: String,
        val duration: String,
        val price: String,
        val discount: String,
        val oldPrice: String,
        var selected: Boolean,
        val serviceId: Int) : Serializable