package pe.gob.fondepes.demo.portal.certificaciones.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.gob.fondepes.demo.portal.certificaciones.R
import pe.gob.fondepes.demo.portal.certificaciones.presentation.data.Notification
import pe.gob.fondepes.demo.portal.certificaciones.presentation.data.NotificationState

class NotificationAdapter(private val notifications: List<Notification>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    inner class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val bodyTextView: TextView = view.findViewById(R.id.bodyTextView)
        val actionButton: Button = view.findViewById(R.id.actionButton)
        val infoIcon: ImageView = view.findViewById(R.id.infoIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        holder.titleTextView.text = notification.title
        holder.bodyTextView.text = notification.body

        // Control visibility based on state
        when (notification.state) {
            NotificationState.WITH_BUTTON -> {
                holder.actionButton.visibility = View.VISIBLE
                holder.infoIcon.visibility = View.GONE
            }
            NotificationState.WITH_ICON -> {
                holder.actionButton.visibility = View.GONE
                holder.infoIcon.visibility = View.VISIBLE
            }
            NotificationState.DEFAULT -> {
                holder.actionButton.visibility = View.GONE
                holder.infoIcon.visibility = View.GONE
            }
        }

        // Optional: Set action for the button or icon
        holder.actionButton.setOnClickListener {
            // Handle button click (e.g., perform registration)
        }

        holder.infoIcon.setOnClickListener {
            // Handle info icon click (e.g., show more details in a dialog or new activity)
        }
    }

    override fun getItemCount(): Int = notifications.size
}