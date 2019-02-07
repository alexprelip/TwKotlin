package alexpr.co.uk.twkotlin.models

data class Section(val name:String, val serviceItem:MutableList<ServiceItem>)

data class ServiceItem(val serviceSubItem:MutableList<ServiceModel>, val serviceModel:ServiceModel, val details:String, val genericCategory:String )