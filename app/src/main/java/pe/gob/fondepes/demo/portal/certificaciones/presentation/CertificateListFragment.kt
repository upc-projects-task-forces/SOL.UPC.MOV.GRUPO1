package pe.gob.fondepes.demo.portal.certificaciones.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import pe.gob.fondepes.demo.portal.certificaciones.R
import pe.gob.fondepes.demo.portal.certificaciones.data.TaskRepository
import pe.gob.fondepes.demo.portal.certificaciones.presentation.adapter.CertificateAdapter
import pe.gob.fondepes.demo.portal.certificaciones.presentation.classes.Certificate
import pe.gob.fondepes.demo.portal.certificaciones.presentation.classes.CertificateApiResponse
import pe.gob.fondepes.demo.portal.certificaciones.presentation.classes.CourseApiResponse
import pe.gob.fondepes.demo.portal.certificaciones.presentation.classes.Task
import pe.gob.fondepes.demo.portal.certificaciones.presentation.classes.TaskAPIResponse
import pe.gob.fondepes.demo.portal.certificaciones.presentation.di.DependencyProvider
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class CertificateListFragment : Fragment() {

    private lateinit var taskRepository: TaskRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_certificados, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView: ListView = view.findViewById(R.id.lvCertification)
        val pbCertication: ProgressBar = view.findViewById(R.id.pbCertification)
        taskRepository = DependencyProvider.provideTaskRepository(requireContext())
        pbCertication.visibility = View.VISIBLE
        listView.visibility = View.GONE
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val tasks = getTasks()
                val adapter = CertificateAdapter(requireContext(), tasks)
                listView.adapter = adapter
                listView.setOnItemClickListener { _, _, position, _ ->
                    val selectedCertificate = tasks[position]
                    val intent =
                        Intent(requireContext(), CertificateDetailActivity::class.java).apply {
                            putExtra("certificate", selectedCertificate)
                            Log.e("ALERT123", selectedCertificate.toString())
                            //putExtra("certificado", selectedCertificate)
                        }
                    startActivity(intent)
                }
                pbCertication.visibility = View.GONE
                listView.visibility = View.VISIBLE
            } catch (e: Exception) {
                Log.e("ErrorTASKS", "Error fetching tasks: ${e.message}")
                pbCertication.visibility = View.GONE
            }
        }
    }

    private suspend fun getTasks(): ArrayList<Certificate> {
        val tasksResponseJson = taskRepository.fetchTasks()
        val tasksResponse = TaskAPIResponse.fromJson(tasksResponseJson.toString())
        return mappingTaskResponse(tasksResponse)
    }

    private fun mappingTaskResponse(tasksResponse: TaskAPIResponse): ArrayList<Certificate> {
        val certificates: ArrayList<Certificate> = arrayListOf()
        tasksResponse.forEach { (id, task) ->
            certificates.add(addCertificate(id, task))
        }
        return certificates
    }

    private fun addCertificate(id: String, task: Task): Certificate {
        val course: CourseApiResponse? = task.course
        val cer: CertificateApiResponse? = task.certificate
        return Certificate(
            id = id,
            title = course?.name ?: "",
            description = course?.description ?: "",
            status = resolveStatus(task),
            expirationDate = formatDate(task.updatedAt),
            instructor = course?.instructor?.name ?: "",
            timeLimit = formatDate(task.updatedAt),
            url = cer?.url ?: ""
        )
    }

    private fun resolveStatus(task: Task): String {
        return when (task.status) {
            "COMPLETED_AWAITING_CERTIFICATE" -> "CERTIFICADO EN PROCESO"
            "COMPLETED" -> "COMPLETADO"
            "FINISHED" -> "FINALIZADO"
            else -> "PENDIENTE"
        }
    }

    private fun formatDate(dateString: String?): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        return try {
            dateString?.let {
                val date = inputFormat.parse(it)
                outputFormat.format(date!!)
            } ?: ""
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CertificateListFragment()
    }
}