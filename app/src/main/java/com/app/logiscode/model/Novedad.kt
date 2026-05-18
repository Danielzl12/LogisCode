package com.app.logiscode.model

data class Novedad(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val type: NovedadType
)

enum class NovedadType { DESVIO, RETRASO, SUSPENSION, INFO }
