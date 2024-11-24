package pe.gob.fondepes.demo.portal.certificaciones.data

import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import pe.gob.fondepes.demo.portal.certificaciones.data.volley.VolleySingleton

class ApiClient(
    private val volleySingleton: VolleySingleton
) {

    fun get(
        url: String,
        successCallback: (JSONObject) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                successCallback(response)
            },
            { error ->
                errorCallback(error)
            }
        )

        volleySingleton.addToRequestQueue(jsonObjectRequest)
    }

    fun post(
        url: String,
        body: JSONObject,
        successCallback: (JSONObject) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, body,
            { response ->
                successCallback(response)
            },
            { error ->
                errorCallback(error)
            }
        )

        volleySingleton.addToRequestQueue(jsonObjectRequest)
    }

    fun login(
        email: String,
        password: String,
        successCallback: (JSONObject) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}
