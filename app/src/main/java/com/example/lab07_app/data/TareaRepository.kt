package com.example.lab07_app.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TareaRepository(private val tareaDao: TareaDao) {

    fun getAllTareas(): Flow<List<Tarea>> {
        return tareaDao.getAllTareas()
    }

    suspend fun insertTarea(tarea: Tarea) {
        withContext(Dispatchers.IO) {
            tareaDao.insertTarea(tarea)
        }
    }

    suspend fun updateTarea(tarea: Tarea) {
        withContext(Dispatchers.IO) {
            tareaDao.updateTarea(tarea)
        }
    }

    suspend fun deleteTarea(tarea: Tarea) {
        withContext(Dispatchers.IO) {
            tareaDao.deleteTarea(tarea)
        }
    }

    suspend fun deleteAllTareas() {
        withContext(Dispatchers.IO) {
            tareaDao.deleteAllTareas()
        }
    }
}
