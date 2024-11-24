package pe.gob.fondepes.demo.portal.certificaciones.presentation.di

import android.content.Context
import pe.gob.fondepes.demo.portal.certificaciones.data.ApiClient
import pe.gob.fondepes.demo.portal.certificaciones.data.LoginRepository
import pe.gob.fondepes.demo.portal.certificaciones.data.NotificationRepository
import pe.gob.fondepes.demo.portal.certificaciones.data.SecurePreferences
import pe.gob.fondepes.demo.portal.certificaciones.data.TaskRepository
import pe.gob.fondepes.demo.portal.certificaciones.data.volley.VolleySingleton

object DependencyProvider {
    private var volleySingleton: VolleySingleton? = null

    private fun provideVolleySingleton(context: Context): VolleySingleton {
        if (volleySingleton == null) {
            volleySingleton = VolleySingleton.getInstance(context)
        }
        return volleySingleton!!
    }

    private fun provideApiClient(context: Context): ApiClient {
        return ApiClient(provideVolleySingleton(context))
    }

    fun provideTaskRepository(context: Context): TaskRepository {
        val apiClient = provideApiClient(context)
        val securePreferences = SecurePreferences(context)
        return TaskRepository(apiClient, securePreferences)
    }


    fun provideNotificationRepository(context: Context): NotificationRepository {
        val apiClient = provideApiClient(context)
        val securePreferences = SecurePreferences(context)
        return NotificationRepository(apiClient, securePreferences)
    }

    fun provideLoginRepository(context: Context): LoginRepository {
        val apiClient = provideApiClient(context)
        val securePreferences = SecurePreferences(context)
        return LoginRepository(apiClient, securePreferences)
    }
}
