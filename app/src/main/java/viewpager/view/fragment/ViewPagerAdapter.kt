package viewpager.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import login.model.WebService

class ViewPagerAdapter(fragmentActivity: FragmentActivity, var /*webservice*/webserviceList: List<WebService>) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return webserviceList.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPagerFragment()
        val args = Bundle()
        args.putParcelable("webservice", webserviceList[position])/*webserviceList[position]*/
//        args.putString("label", "Fragment Number: ${position + 1}")
        fragment.arguments = args
        return fragment
    }
}