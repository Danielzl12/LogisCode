package com.app.logiscode.repository

import com.app.logiscode.model.BusLocation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BusTrackingRepository {

    private val route = listOf(
        4.6097 to -74.0817,
        4.6110 to -74.0830,
        4.6125 to -74.0800,
        4.6140 to -74.0785,
        4.6120 to -74.0770,
        4.6100 to -74.0790
    )

    fun getBusLocationFlow(): Flow<BusLocation> = flow {
        var index = 0
        while (true) {
            val (lat, lng) = route[index % route.size]
            emit(
                BusLocation(
                    busId = "BUS-001",
                    latitude = lat,
                    longitude = lng,
                    routeName = "Ruta Escolar Norte",
                    lastUpdated = System.currentTimeMillis()
                )
            )
            index++
            delay(3000L)
        }
    }
}
