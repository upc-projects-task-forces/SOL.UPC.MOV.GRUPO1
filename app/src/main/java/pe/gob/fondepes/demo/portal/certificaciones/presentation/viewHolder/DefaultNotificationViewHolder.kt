package pe.gob.fondepes.demo.portal.certificaciones.presentation.viewHolder


import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.gob.fondepes.demo.portal.certificaciones.R
import pe.gob.fondepes.demo.portal.certificaciones.presentation.data.Notification

class DefaultNotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val titleTextView: TextView = view.findViewById(R.id.titleTextView)
    private val bodyTextView: TextView = view.findViewById(R.id.bodyTextView)

    fun bind(notification: Notification) {
        titleTextView.text = notification.title
        bodyTextView.text = notification.description
    }
}

class NotificationWithButtonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val titleTextView: TextView = view.findViewById(R.id.titleTextView)
    private val bodyTextView: TextView = view.findViewById(R.id.bodyTextView)
    private val actionButton: Button = view.findViewById(R.id.actionButton)

    fun bind(notification: Notification) {
        titleTextView.text = notification.title
        bodyTextView.text = notification.description
        actionButton.setOnClickListener {
        }
    }
}

class NotificationWithIconViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val titleTextView: TextView = view.findViewById(R.id.titleTextView)
    private val bodyTextView: TextView = view.findViewById(R.id.bodyTextView)
    private val infoIcon: ImageView = view.findViewById(R.id.infoIcon)

    fun bind(notification: Notification) {
        titleTextView.text = notification.title
        bodyTextView.text = notification.description
    }
}