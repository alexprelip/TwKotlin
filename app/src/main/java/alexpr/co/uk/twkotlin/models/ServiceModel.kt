package alexpr.co.uk.twkotlin.models

import java.io.Serializable

data class ServiceModel(val serviceName: String, val duration: String, val price: String, val discount: String, val oldPrice: String, var selected:Boolean) : Serializable