package com.app.logiscode.model

data class BusLocation(
    val busId: String,
    val routeId: String,
    val latitude: Double,
    val longitude: Double,
    val routeName: String,
    val lastUpdated: Long
)
