package com.app.logiscode.model

data class Reporte(
    val id: String,
    val routeName: String,
    val incidentType: String,
    val date: String,
    val status: ReporteStatus
)

enum class ReporteStatus { EN_REVISION, SOLUCIONADO, REGISTRADO }
