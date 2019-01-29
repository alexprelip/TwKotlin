package alexpr.co.uk.twkotlin.models

import java.io.Serializable

data class PlaceModel(
        val imageUrl: String,
        val name: String,
        val rating: Double,
        val reviewCount: String,
        val address: String,
        val award: String,
        val serviceHighlight: List<ServiceModel>
) : Serializable