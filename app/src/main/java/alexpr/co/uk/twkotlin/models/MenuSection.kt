package alexpr.co.uk.twkotlin.models

import java.io.Serializable

data class MenuSection(val imageUri: String, val title: String, var menuItems: MutableList<MenuItem>) : Serializable