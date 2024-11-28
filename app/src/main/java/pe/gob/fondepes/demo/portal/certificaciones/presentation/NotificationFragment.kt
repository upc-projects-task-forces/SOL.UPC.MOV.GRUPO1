package pe.gob.fondepes.demo.portal.certificaciones.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.json.JSONObject
import pe.gob.fondepes.demo.portal.certificaciones.data.ApiClient
import pe.gob.fondepes.demo.portal.certificaciones.data.NotificationRepository
import pe.gob.fondepes.demo.portal.certificaciones.data.SecurePreferences
import pe.gob.fondepes.demo.portal.certificaciones.data.volley.VolleySingleton
import pe.gob.fondepes.demo.portal.certificaciones.presentation.adapter.NotificationAdapter
import pe.gob.fondepes.demo.portal.certificaciones.presentation.data.Notification
import pe.gob.fondepes.demo.portal.certificaciones.databinding.FragmentNotificationBinding
import pe.gob.fondepes.demo.portal.certificaciones.presentation.data.NotificationAction


/**
 * A simple [Fragment] subclass.
 * Use the [NotificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationFragment : Fragment() {
    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!
    private lateinit var repository: NotificationRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiClient = pe.gob.fondepes.demo.portal.certificaciones.data.ApiClient(
            VolleySingleton.getInstance(requireContext())
        )
        val securePreferences = SecurePreferences(requireContext())  // Asegúrate de tener implementada esta clase
        repository = NotificationRepository(apiClient, securePreferences)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchNotifications() // Llama a la función de carga de notificaciones
    }

    private fun fetchNotifications() {
        val userId = "AIzaSyAv1OGGQYlECPRr0AXu2bCPzbCU0nwpwC0"

        repository.fetchNotifications(
            userId = userId,
            successCallback = { response ->
                val notifications = parseNotifications(response)
                if (notifications.isNotEmpty()) {
                    setupRecyclerView(notifications) // Actualiza RecyclerView con los datos obtenidos
                } else {
                    Toast.makeText(
                        requireContext(),
                        "No hay notificaciones disponibles",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            errorCallback = { error ->
                Toast.makeText(
                    requireContext(),
                    "Error al cargar notificaciones: ${error.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        )
    }

    private fun parseNotifications(response: JSONObject): List<Notification> {
        val notifications = mutableListOf<Notification>()

        val keys = response.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            val notificationJson = response.getJSONObject(key)

            val actionJson = notificationJson.optJSONObject("action")
            val action = if (actionJson != null) {
                NotificationAction(
                    label = actionJson.getString("label"),
                    target = actionJson.getString("target")
                )
            } else {
                null
            }

            val notification = Notification(
                notificationId = notificationJson.getString("notificationId"),
                title = notificationJson.getString("title"),
                description = notificationJson.getString("description"),
                type = notificationJson.getString("type"),
                createdAt = notificationJson.getString("createdAt"),
                action = action
            )

            notifications.add(notification)
        }

        return notifications
    }

    private fun setupRecyclerView(notifications: List<Notification>) {
        val adapter = NotificationAdapter(notifications)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}