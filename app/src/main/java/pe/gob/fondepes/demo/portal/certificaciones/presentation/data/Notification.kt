package pe.gob.fondepes.demo.portal.certificaciones.presentation.data

data class Notification(
    val notificationId: String,
    val title: String,
    val description: String,
    val type: String,
    val createdAt: String,
    val action: NotificationAction? = null
)

data class NotificationAction(
    val label: String,
    val target: String
)