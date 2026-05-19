package com.app.logiscode.ui.screen

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.logiscode.R
import com.app.logiscode.viewmodel.MapViewModel
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    viewModel: MapViewModel = viewModel()
) {
    val busLocations by viewModel.busLocations.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val routes = viewModel.routes

    var selectedRouteIds by rememberSaveable {
        mutableStateOf(routes.map { it.id }.toSet())
    }

    val markerIcons = remember(routes) {
        routes.associate { route ->
            route.id to getColoredBusIcon(context, route.colorHex)
        }
    }

    val allPoints = routes.flatMap { it.waypoints }
    val boundingBox = remember(allPoints) {
        val lats = allPoints.map { it.first }
        val lngs = allPoints.map { it.second }
        BoundingBox(
            lats.max() + 0.01,
            lngs.max() + 0.01,
            lats.min() - 0.01,
            lngs.min() - 0.01
        )
    }

    val mapView = remember {
        MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            post {
                zoomToBoundingBox(boundingBox, true, 60)
            }
        }
    }

    DisposableEffect(Unit) {
        mapView.onResume()
        onDispose {
            mapView.onPause()
            mapView.onDetach()
        }
    }

    val visibleRoutes = routes.filter { it.id in selectedRouteIds }
    val visibleLocations = busLocations.filter { it.routeId in selectedRouteIds }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Mapa de Rutas") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            routes.forEach { route ->
                val isSelected = route.id in selectedRouteIds
                FilterChip(
                    selected = isSelected,
                    onClick = {
                        selectedRouteIds = if (isSelected) {
                            selectedRouteIds - route.id
                        } else {
                            selectedRouteIds + route.id
                        }
                    },
                    label = { Text(route.name, style = MaterialTheme.typography.labelMedium) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.DirectionsBus,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = if (isSelected) Color(route.colorHex) else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(route.colorHex).copy(alpha = 0.15f),
                        selectedLabelColor = Color(route.colorHex)
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { mapView },
            update = { map ->
                map.overlays.clear()

                visibleRoutes.forEach { route ->
                    val polyline = Polyline().apply {
                        outlinePaint.color = route.colorHex
                        outlinePaint.strokeWidth = 8f
                        setPoints(route.waypoints.map { (lat, lng) -> GeoPoint(lat, lng) })
                    }
                    map.overlays.add(polyline)
                }

                visibleLocations.forEach { loc ->
                    val marker = Marker(map).apply {
                        position = GeoPoint(loc.latitude, loc.longitude)
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
                        title = loc.routeName
                        snippet = "Bus: ${loc.busId}"
                        markerIcons[loc.routeId]?.let { icon = it }
                    }
                    map.overlays.add(marker)
                }

                map.invalidate()
            }
        )
    }
}

private fun getColoredBusIcon(context: Context, colorInt: Int): Drawable {
    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_bus)!!.mutate()
    @Suppress("DEPRECATION")
    drawable.setColorFilter(colorInt, PorterDuff.Mode.SRC_IN)
    return drawable
}
