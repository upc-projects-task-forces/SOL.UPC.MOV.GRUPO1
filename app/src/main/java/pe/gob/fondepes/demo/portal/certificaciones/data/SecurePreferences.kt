package pe.gob.fondepes.demo.portal.certificaciones.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SecurePreferences(context: Context) {
    private val sharedPreferences = EncryptedSharedPreferences.create(
        "secure_prefs",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("firebaseToken", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("firebaseToken", null)
    }

    fun saveUserID(userID: String) {
        sharedPreferences.edit().putString("userID", userID).apply()
    }

    fun getUserID(): String? {
        return sharedPreferences.getString("userID", null)
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    fun saveRefreshToken(refreshToken: String) {
        sharedPreferences.edit().putString("firebaseRefreshToken", refreshToken).apply()
    }
}

