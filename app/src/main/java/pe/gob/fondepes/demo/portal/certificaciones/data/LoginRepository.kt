package pe.gob.fondepes.demo.portal.certificaciones.data

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
        TODO("Not yet implemented")
    }
}