package pe.gob.fondepes.demo.portal.certificaciones.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import pe.gob.fondepes.demo.portal.certificaciones.R
import pe.gob.fondepes.demo.portal.certificaciones.presentation.data.Notification

class NotificationAdapter(private var notifications: List<Notification>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = notifications[position]
        holder.bind(notification)
    }

    override fun getItemCount(): Int = notifications.size

    // Cambiar la lista de notificaciones
    fun updateData(newNotifications: List<Notification>) {
        notifications = newNotifications // Ahora podemos reasignar la lista
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val bodyTextView: TextView = itemView.findViewById(R.id.bodyTextView)
        private val actionButton: Button = itemView.findViewById(R.id.actionButton)
        private val infoIcon: ImageView = itemView.findViewById(R.id.infoIcon)

        fun bind(notification: Notification) {
            titleTextView.text = notification.title
            bodyTextView.text = notification.description

            // Configurar el botón o el icono en función de la acción
            if (notification.action != null) {
                actionButton.visibility = View.VISIBLE
                actionButton.text = notification.action.label
                actionButton.setOnClickListener {
                    // Manejar la acción del botón
                    handleAction(notification.action.target)
                }
                infoIcon.visibility = View.GONE
            } else {
                actionButton.visibility = View.GONE
                infoIcon.visibility = View.VISIBLE
            }
        }

        private fun handleAction(target: String) {
            // Implementar lógica para manejar la acción del botón
            Toast.makeText(itemView.context, "Acción: $target", Toast.LENGTH_SHORT).show()
        }
    }
}