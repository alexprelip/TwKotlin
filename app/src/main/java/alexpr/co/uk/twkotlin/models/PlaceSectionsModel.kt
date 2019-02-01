package alexpr.co.uk.twkotlin.models

data class Section(val name:String, val serviceItem:List<ServiceItem>)

data class ServiceItem(val serviceSubItem:List<ServiceModel>, val serviceModel:ServiceModel, val details:String, val genericCategory:String )