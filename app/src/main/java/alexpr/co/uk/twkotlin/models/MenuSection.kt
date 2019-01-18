package alexpr.co.uk.twkotlin.models

import android.net.Uri

data class MenuSection(val imageUri : Uri, val title:String, val menuItems : MutableList<MenuItem>)