package pe.gob.fondepes.demo.portal.certificaciones.data

import org.json.JSONObject
import pe.gob.fondepes.demo.portal.certificaciones.data.firebase.FirebaseApi

class NotificationRepository(
    private val apiClient: ApiClient,
    private val securePreferences: SecurePreferences
) {

    fun fetchNotifications(
        successCallback: (JSONObject) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        val token = securePreferences.getToken()
        val userId = securePreferences.getUserID()

        if (token.isNullOrEmpty()) {
            errorCallback(Throwable("Token is missing"))

            return
        }
        if (userId.isNullOrEmpty()) {
            errorCallback(Throwable("User id is missing"))
            return
        }

        val url = FirebaseApi.NOTIFICATIONS_ENDPOINT.replace("{userId}", userId) + "?auth=$token"

        apiClient.get(
            url = url,
            successCallback = successCallback,
            errorCallback = errorCallback
        )
    }
}