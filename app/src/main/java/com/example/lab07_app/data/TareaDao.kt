package com.example.lab07_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TareaDao {
    @Query("SELECT * FROM tareas ORDER BY id DESC")
    fun getAllTareas(): Flow<List<Tarea>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTarea(tarea: Tarea)

    @Update
    fun updateTarea(tarea: Tarea)

    @Delete
    fun deleteTarea(tarea: Tarea)

    @Query("DELETE FROM tareas")
    fun deleteAllTareas()
}
