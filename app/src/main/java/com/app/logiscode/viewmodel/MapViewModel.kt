package com.app.logiscode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.logiscode.model.BusLocation
import com.app.logiscode.model.getDefaultRoutes
import com.app.logiscode.repository.BusTrackingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint

class MapViewModel : ViewModel() {

    private val repository = BusTrackingRepository()

    val routes = getDefaultRoutes()

    private val _busLocations = MutableStateFlow<List<BusLocation>>(emptyList())
    val busLocations: StateFlow<List<BusLocation>> = _busLocations.asStateFlow()

    val bogotaCenter = GeoPoint(4.6400, -74.0700)

    init {
        viewModelScope.launch {
            repository.getAllBusLocationsFlow().collect { locations ->
                _busLocations.value = locations
            }
        }
    }
}
