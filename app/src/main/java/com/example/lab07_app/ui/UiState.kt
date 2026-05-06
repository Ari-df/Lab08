package com.example.lab07_app.ui

import com.example.lab07_app.data.Tarea

data class UiState(
    val tareas: List<Tarea> = emptyList(),
    val filtroActivo: FiltroTarea = FiltroTarea.TODAS,
    val textoInput: String = ""
)
