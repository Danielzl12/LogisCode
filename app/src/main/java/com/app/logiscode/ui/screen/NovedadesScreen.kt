package com.app.logiscode.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AltRoute
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.logiscode.model.Novedad
import com.app.logiscode.model.NovedadType

private val dummyNovedades = listOf(
    Novedad(
        id = "1",
        title = "Desvío en Ruta NQS",
        description = "Desvío en Ruta 3 por mantenimiento vial en la Cll 72. Se estima normalización para las 2:00 PM.",
        date = "22 May 2026",
        type = NovedadType.DESVIO
    ),
    Novedad(
        id = "2",
        title = "Retraso en Ruta Caracas",
        description = "Retraso generalizado en la Caracas por movilización ciudadana a la altura de la Cll 45. Tiempo estimado de demora: 25 minutos.",
        date = "21 May 2026",
        type = NovedadType.RETRASO
    ),
    Novedad(
        id = "3",
        title = "Suspensión de servicio",
        description = "Circular: Mañana viernes no habrá servicio de ruta por jornada pedagógica institucional. El servicio se reanuda el lunes.",
        date = "20 May 2026",
        type = NovedadType.SUSPENSION
    ),
    Novedad(
        id = "4",
        title = "Nueva parada en Ruta Séptima",
        description = "Se agrega parada temporal en la Cra 7 con Cll 100 por solicitud de padres de familia del sector norte.",
        date = "19 May 2026",
        type = NovedadType.INFO
    ),
    Novedad(
        id = "5",
        title = "Desvío en Ruta 68",
        description = "Cierre parcial en la Av Cra 68 entre Cll 80 y Cll 72 por obras de TransMilenio. Desvío por Cra 70.",
        date = "18 May 2026",
        type = NovedadType.DESVIO
    ),
    Novedad(
        id = "6",
        title = "Retraso en Ruta UMB",
        description = "Ruta UMB Circunvalar presenta retraso de 20 minutos por congestión en la subida a la Circunvalar desde Cra 61.",
        date = "18 May 2026",
        type = NovedadType.RETRASO
    ),
    Novedad(
        id = "7",
        title = "Mantenimiento programado",
        description = "Los buses BUS-002 y BUS-004 entrarán a mantenimiento preventivo este sábado. No se afecta el servicio entre semana.",
        date = "17 May 2026",
        type = NovedadType.INFO
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NovedadesScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Novedades") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(dummyNovedades) { novedad ->
                NovedadCard(novedad)
            }
        }
    }
}

@Composable
private fun NovedadCard(novedad: Novedad) {
    val (icon, iconColor) = novedadTypeVisuals(novedad.type)

    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = novedad.date,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = novedad.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = novedad.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private fun novedadTypeVisuals(type: NovedadType): Pair<ImageVector, Color> = when (type) {
    NovedadType.DESVIO -> Icons.Filled.AltRoute to Color(0xFFE65100)
    NovedadType.RETRASO -> Icons.Filled.Schedule to Color(0xFFF9A825)
    NovedadType.SUSPENSION -> Icons.Filled.Cancel to Color(0xFFC62828)
    NovedadType.INFO -> Icons.Filled.Info to Color(0xFF1565C0)
}
