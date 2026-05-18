package com.app.logiscode.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.logiscode.model.Reporte
import com.app.logiscode.model.ReporteStatus

private val dummyReportes = listOf(
    Reporte("1", "Ruta Séptima", "Exceso de velocidad", "18 May 2026", ReporteStatus.EN_REVISION),
    Reporte("2", "Ruta UMB Circunvalar", "Fallo mecánico", "17 May 2026", ReporteStatus.SOLUCIONADO),
    Reporte("3", "Ruta Carrera 68", "Estudiante no abordó", "17 May 2026", ReporteStatus.REGISTRADO),
    Reporte("4", "Ruta Caracas", "Desvío no autorizado", "16 May 2026", ReporteStatus.EN_REVISION),
    Reporte("5", "Ruta NQS (Cra 30)", "Retraso mayor a 15 min", "15 May 2026", ReporteStatus.SOLUCIONADO),
    Reporte("6", "Ruta Séptima", "Puerta trasera defectuosa", "14 May 2026", ReporteStatus.SOLUCIONADO),
    Reporte("7", "Ruta Caracas", "Conductor no identificado", "13 May 2026", ReporteStatus.REGISTRADO),
    Reporte("8", "Ruta Carrera 68", "Sobrecupo reportado", "12 May 2026", ReporteStatus.EN_REVISION)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportesScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reportes") },
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
            items(dummyReportes) { reporte ->
                ReporteCard(reporte)
            }
        }
    }
}

@Composable
private fun ReporteCard(reporte: Reporte) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = reporte.routeName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                StatusBadge(reporte.status)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = reporte.incidentType,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = reporte.date,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun StatusBadge(status: ReporteStatus) {
    val (backgroundColor, label) = when (status) {
        ReporteStatus.EN_REVISION -> Color(0xFFFFF176) to "En Revisión"
        ReporteStatus.SOLUCIONADO -> Color(0xFFA5D6A7) to "Solucionado"
        ReporteStatus.REGISTRADO -> Color(0xFFBDBDBD) to "Registrado"
    }
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF212121)
        )
    }
}
