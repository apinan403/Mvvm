package home.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvm.databinding.FragmentHomeBinding
import home.viewmodel.HomeViewModel
import login.model.UserModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel = HomeViewModel() /*New OBJ*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userData = arguments?.getParcelable<UserModel>("FragUserData")
        val textHi = arguments?.getString("message")

        userData?.entries?.user?.let {
            binding.tvId.text = it.uniqueID.toString()
            binding.tvName.text = it.name
            binding.tvRole.text = it.role
        }
    }
}