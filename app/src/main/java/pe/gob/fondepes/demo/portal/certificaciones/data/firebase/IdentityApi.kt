package pe.gob.fondepes.demo.portal.certificaciones.data.firebase

object IdentityApi {
    private const val BASE_URL = "https://identitytoolkit.googleapis.com/v1/accounts"
    private const val apiKey = pe.gob.fondepes.demo.portal.certificaciones.BuildConfig.FIREBASE_API_KEY
    const val LOGIN_URL = "$BASE_URL:signInWithPassword?key=$apiKey"
}