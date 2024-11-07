package pe.gob.fondepes.demo.portal.certificaciones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import pe.gob.fondepes.demo.portal.certificaciones.adapter.NotificationAdapter
import pe.gob.fondepes.demo.portal.certificaciones.data.Notification
import pe.gob.fondepes.demo.portal.certificaciones.data.NotificationState
import pe.gob.fondepes.demo.portal.certificaciones.databinding.FragmentNotificationBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationFragment : Fragment() {
    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val notifications = listOf(
            Notification(
                title = "Nueva certificación",
                body = "Seguridad ante derrames de petróleo",
                state = NotificationState.WITH_BUTTON
            ),
            Notification(
                title = "Próxima renovación",
                body = "Certificación Liberación de tortugas",
                state = NotificationState.WITH_ICON
            ),
            Notification(
                title = "Recordatorio",
                body = "Certificación de primeros auxilios expira pronto",
                state = NotificationState.DEFAULT
            )
        )

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = NotificationAdapter(notifications)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment NotificationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            NotificationFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}