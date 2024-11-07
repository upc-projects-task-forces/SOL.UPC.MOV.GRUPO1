package pe.gob.fondepes.demo.portal.certificaciones.data

data class Notification(
    val title: String,
    val body: String,
    val state: NotificationState
)

enum class NotificationState {
    DEFAULT,
    WITH_BUTTON,
    WITH_ICON
}