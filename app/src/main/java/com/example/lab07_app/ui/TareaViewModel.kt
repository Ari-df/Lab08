package com.example.lab07_app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab07_app.data.Tarea
import com.example.lab07_app.data.TareaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TareaViewModel(private val repository: TareaRepository) : ViewModel() {

    private val _filtroActivo = MutableStateFlow(FiltroTarea.TODAS)
    private val _textoInput = MutableStateFlow("")

    val uiState: StateFlow<UiState> = combine(
        repository.getAllTareas(),
        _filtroActivo,
        _textoInput
    ) { tareas, filtro, texto ->
        val tareasFiltradas = when (filtro) {
            FiltroTarea.TODAS -> tareas
            FiltroTarea.PENDIENTES -> tareas.filter { !it.completada }
            FiltroTarea.COMPLETADAS -> tareas.filter { it.completada }
        }
        UiState(
            tareas = tareasFiltradas,
            filtroActivo = filtro,
            textoInput = texto
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UiState()
    )

    fun actualizarTexto(nuevoTexto: String) {
        _textoInput.value = nuevoTexto
    }

    fun cambiarFiltro(nuevoFiltro: FiltroTarea) {
        _filtroActivo.value = nuevoFiltro
    }

    fun agregarTarea() {
        val descripcion = _textoInput.value.trim()
        if (descripcion.isNotEmpty()) {
            viewModelScope.launch {
                repository.insertTarea(Tarea(descripcion = descripcion))
                _textoInput.value = "" // Limpiar el input
            }
        }
    }

    fun toggleCompletada(tarea: Tarea) {
        viewModelScope.launch {
            repository.updateTarea(tarea.copy(completada = !tarea.completada))
        }
    }

    fun eliminarTarea(tarea: Tarea) {
        viewModelScope.launch {
            repository.deleteTarea(tarea)
        }
    }

    fun eliminarTodas() {
        viewModelScope.launch {
            repository.deleteAllTareas()
        }
    }
}
