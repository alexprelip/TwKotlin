package alexpr.co.uk.twkotlin.models

data class MenuSection(val imageUri : String, val title:String, var menuItems : MutableList<MenuItem>)