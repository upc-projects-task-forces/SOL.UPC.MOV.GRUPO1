package pe.gob.fondepes.demo.portal.certificaciones.data

import org.json.JSONObject
import pe.gob.fondepes.demo.portal.certificaciones.data.firebase.FirebaseApi
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class TaskRepository(
    private val apiClient: ApiClient,
    private val securePreferences: SecurePreferences
) {

    suspend fun fetchTasks(): JSONObject {
        val token = securePreferences.getToken()
        val url = FirebaseApi.TASKS_ENDPOINT.replace("{userId}",
            securePreferences.getUserID().toString()) + "?auth=$token"
        return suspendCoroutine { continuation ->
            apiClient.get(url,
                successCallback = { response ->
                    continuation.resume(response)
                },
                errorCallback = {error ->
                    continuation.resumeWithException(error)
                })
        }
    }
}
