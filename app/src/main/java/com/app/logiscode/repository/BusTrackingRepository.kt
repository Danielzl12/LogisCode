package com.app.logiscode.repository

import com.app.logiscode.model.BusLocation
import com.app.logiscode.model.getDefaultRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BusTrackingRepository {

    fun getAllBusLocationsFlow(): Flow<List<BusLocation>> = flow {
        val routes = getDefaultRoutes()
        val indices = IntArray(routes.size)
        while (true) {
            val locations = routes.mapIndexed { i, route ->
                val (lat, lng) = route.waypoints[indices[i] % route.waypoints.size]
                BusLocation(
                    busId = "BUS-${String.format("%03d", i + 1)}",
                    routeId = route.id,
                    latitude = lat,
                    longitude = lng,
                    routeName = route.name,
                    lastUpdated = System.currentTimeMillis()
                )
            }
            emit(locations)
            indices.forEachIndexed { i, _ -> indices[i]++ }
            delay(3000L)
        }
    }
}
