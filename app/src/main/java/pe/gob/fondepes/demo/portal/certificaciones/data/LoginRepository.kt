package pe.gob.fondepes.demo.portal.certificaciones.data

import android.util.Log

class LoginRepository(
    private val apiClient: ApiClient,
    private val securePreferences: SecurePreferences
) {

    fun login(
        email: String,
        password: String,
        successCallback: () -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        apiClient.login(
            email = email,
            password = password,
            successCallback = { response ->
                // Parse and save the token
                val token = response.optString("idToken", "")
                val refreshToken = response.optString("refreshToken", "")
                val userID = response.optString("localId","")
                if (token.isNotEmpty()) {
                    securePreferences.saveToken(token)
                    securePreferences.saveRefreshToken(refreshToken)
                    securePreferences.saveUserID(userID)
                    successCallback()
                } else {
                    errorCallback(Throwable("Token not found in the response"))
                }
            },
            errorCallback = { error ->
                errorCallback(error)
            }
        )
    }
}