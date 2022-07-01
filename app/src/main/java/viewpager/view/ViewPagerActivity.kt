package viewpager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mvvm.databinding.ActivityViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator
import home.view.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_view_pager.*
import login.model.UserModel
import login.model.WebService
import viewpager.view.fragment.ViewPagerAdapter
import viewpager.view.fragment.ViewPagerFragment

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        val view = binding.root
//        setContentView(R.layout.activity_view_pager)
        setContentView(view)

        val fragment = ViewPagerFragment()
        val args = Bundle()
        args.putString("message", "Hi")
        fragment.arguments = args

        setupViewPager2()
    }

    private fun setupViewPager2() {
        val webserviceData = intent.getParcelableExtra<UserModel>("WsData")
        val webservice = webserviceData?.entries?.webService?.filterIndexed{ index, webserviceLenght -> index < 3 }
//        val webservice = ArrayList<WebService>()

        webservice?.let {
            binding.activityViewPagerVpViewPager.adapter = ViewPagerAdapter(this, it)
            TabLayoutMediator(binding.activityViewPagerTLTabLayout, activity_view_pager_Vp_viewPager){tab, position ->
            }.attach()
        } ?: Toast.makeText(this, "Load Data Failed", Toast.LENGTH_SHORT).show()

    }
}

//        binding.activityViewPagerVpViewPager.setPageTransformer(ZoomOutPageTransformer())
//        val colorList = arrayListOf("#00ff00", "#ff0000", "#0000ff", "#000000")
/*        val webserviceList = webserviceData?.entries?.webService?.let {
            arrayListOf(it.get(0).key, it.get(1).key, it.get(2).key) }*/

// This function handles back button press. When user press back button, previous view in viewPager2
// is displayed.
/*    override fun onBackPressed() {
        val viewPager = binding.viewPager
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }*/