package com.app.logiscode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.logiscode.model.BusLocation
import com.app.logiscode.repository.BusTrackingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint

class MapViewModel : ViewModel() {

    private val repository = BusTrackingRepository()

    private val _busLocation = MutableStateFlow<BusLocation?>(null)
    val busLocation: StateFlow<BusLocation?> = _busLocation.asStateFlow()

    val bogotaCenter = GeoPoint(4.6097, -74.0817)

    init {
        viewModelScope.launch {
            repository.getBusLocationFlow().collect { location ->
                _busLocation.value = location
            }
        }
    }
}
