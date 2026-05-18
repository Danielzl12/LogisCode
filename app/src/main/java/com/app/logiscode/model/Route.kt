package com.app.logiscode.model

data class Route(
    val id: String,
    val name: String,
    val colorHex: Int,
    val waypoints: List<Pair<Double, Double>>
)

fun getDefaultRoutes(): List<Route> = listOf(
    Route(
        id = "ruta-septima",
        name = "Ruta Séptima",
        colorHex = 0xFF1565C0.toInt(),
        waypoints = listOf(
            4.6950 to -74.0308,
            4.6860 to -74.0370,
            4.6740 to -74.0440,
            4.6630 to -74.0530,
            4.6510 to -74.0580,
            4.6400 to -74.0630,
            4.6280 to -74.0670,
            4.6170 to -74.0710,
            4.6097 to -74.0735,
            4.6010 to -74.0760
        )
    ),
    Route(
        id = "ruta-caracas",
        name = "Ruta Caracas",
        colorHex = 0xFFC62828.toInt(),
        waypoints = listOf(
            4.6850 to -74.0480,
            4.6750 to -74.0530,
            4.6650 to -74.0580,
            4.6550 to -74.0620,
            4.6450 to -74.0660,
            4.6350 to -74.0700,
            4.6250 to -74.0740,
            4.6150 to -74.0790,
            4.6050 to -74.0830,
            4.5950 to -74.0870
        )
    ),
    Route(
        id = "ruta-nqs",
        name = "Ruta NQS (Cra 30)",
        colorHex = 0xFF2E7D32.toInt(),
        waypoints = listOf(
            4.6780 to -74.0750,
            4.6700 to -74.0770,
            4.6600 to -74.0800,
            4.6500 to -74.0830,
            4.6400 to -74.0870,
            4.6300 to -74.0910,
            4.6200 to -74.0940,
            4.6100 to -74.0980,
            4.6000 to -74.1010,
            4.5900 to -74.1050
        )
    ),
    Route(
        id = "ruta-68",
        name = "Ruta Carrera 68",
        colorHex = 0xFFE65100.toInt(),
        waypoints = listOf(
            4.6920 to -74.0940,
            4.6820 to -74.0960,
            4.6710 to -74.0970,
            4.6600 to -74.0985,
            4.6490 to -74.1000,
            4.6380 to -74.1020,
            4.6270 to -74.1040,
            4.6160 to -74.1060,
            4.6050 to -74.1080,
            4.5950 to -74.1100
        )
    ),
    Route(
        id = "ruta-umb",
        name = "Ruta UMB Circunvalar",
        colorHex = 0xFF6A1B9A.toInt(),
        waypoints = listOf(
            4.6350 to -74.0890,
            4.6380 to -74.0830,
            4.6400 to -74.0760,
            4.6430 to -74.0700,
            4.6460 to -74.0640,
            4.6500 to -74.0580,
            4.6540 to -74.0520,
            4.6570 to -74.0460,
            4.6590 to -74.0400,
            4.6615 to -74.0350
        )
    )
)
