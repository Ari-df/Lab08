package com.example.lab07_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab07_app.data.TaskMasterDatabase
import com.example.lab07_app.data.TareaRepository
import com.example.lab07_app.ui.MainScreen
import com.example.lab07_app.ui.TareaViewModel
import com.example.lab07_app.ui.theme.Lab07_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Inicializar base de datos y repositorio manualmente para evitar DI (Dagger/Hilt) por simplicidad
        val database = TaskMasterDatabase.getDatabase(this)
        val repository = TareaRepository(database.tareaDao())

        enableEdgeToEdge()
        setContent {
            Lab07_AppTheme {
                val viewModel: TareaViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return TareaViewModel(repository) as T
                        }
                    }
                )
                MainScreen(viewModel = viewModel)
            }
        }
    }
}