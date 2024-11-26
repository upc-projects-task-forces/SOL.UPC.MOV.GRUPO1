package pe.gob.fondepes.demo.portal.certificaciones.presentation.classes

import com.beust.klaxon.Klaxon

private val klaxon = Klaxon()

class TaskAPIResponse(elements: Map<String, Task>) : HashMap<String, Task>(elements) {
    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String): TaskAPIResponse {
            val jsonObject = klaxon.parseJsonObject(java.io.StringReader(json))
            val taskMap = mutableMapOf<String, Task>()

            jsonObject.forEach { (key, value) ->
                if (value is com.beust.klaxon.JsonObject) {
                    val task = klaxon.parse<Task>(value.toJsonString())
                    if (task != null) {
                        taskMap[key] = task
                    }
                }
            }
            return TaskAPIResponse(taskMap)
        }
    }
}

data class Task(
    val certificate: CertificateApiResponse? = null,
    val course: CourseApiResponse? = null,
    val createdAt: String? = null,
    val status: String? = null,
    val updatedAt: String? = null
)

data class CertificateApiResponse(
    val issuedAt: String? = null,
    val url: String? = null,
)

data class CourseApiResponse(
    val courseId: String? = null,
    val description: String? = null,
    val endDate: String? = null,
    val instructor: InstructorApiResponse? = null,
    val name: String? = null,
    val startDate: String? = null,
    val status: String? = null
)

data class InstructorApiResponse(
    val id: String? = null,
    val name: String? = null
)