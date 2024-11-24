package pe.gob.fondepes.demo.portal.certificaciones.data

import org.json.JSONObject
import pe.gob.fondepes.demo.portal.certificaciones.data.firebase.FirebaseApi

class TaskRepository(
    private val apiClient: ApiClient,
    private val securePreferences: SecurePreferences
) {

    fun fetchTasks(
        userId: String,
        successCallback: (JSONObject) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        val token = securePreferences.getToken()
        val url = FirebaseApi.TASKS_ENDPOINT.replace("{userId}", userId) + "?auth=$token"

        apiClient.get(url, successCallback, errorCallback)
    }
}
