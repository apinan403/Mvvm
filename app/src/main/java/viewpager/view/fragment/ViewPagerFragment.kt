package viewpager.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvvm.R
import com.example.mvvm.databinding.FragmentViewPagerBinding
import login.model.WebService

class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        inflater.inflate(R.layout.fragment_view_pager, container, false)
        binding = FragmentViewPagerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupData()
    }

    private fun setupData() {
        binding.label.text = getString(R.string.app_name)
        val WsData = arguments?.getParcelable<WebService>("webservice")
        if (WsData != null) {
            binding.label.text = WsData.key
        }

    }

}

//        val positionLabel = arguments?.getString("label")
//        binding.label.text = positionLabel
//        val color = arguments?.getString("color")
//        binding.root.setBackgroundColor(Color.parseColor(color))