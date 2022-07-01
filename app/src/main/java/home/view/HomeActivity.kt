package home.view

import ApiInterface
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.databinding.ActivityHomeBinding
import home.model.ProductModel
import home.view.fragment.HomeFragment
import home.viewmodel.HomeViewModel
import login.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import viewpager.view.ViewPagerActivity

class HomeActivity : AppCompatActivity()  {
    lateinit var binding: ActivityHomeBinding
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userData = getIntent().getParcelableExtra<UserModel>("UserData")

        userData?.let {
            binding.idTv.text = "Unique Id : ${it.entries.user.uniqueID}"
            binding.nameTv.text = "Name : ${it.entries.user.name}"
            binding.roleTv.text = "Role : ${it.entries.user.role}"
        }

        val fragment = HomeFragment()
        val args = Bundle()
        args.putParcelable("FragUserData", userData) //ค่าส่งได้ แต่ไม่เข้า Fragment
        args.putString("string", "Hi")
        fragment.arguments = args

        binding.btnGetProduct.setOnClickListener {
            getProdtResp()
        }

        binding.BtnActivityHomeGoViewPager.setOnClickListener {
            val intent = Intent(this@HomeActivity, ViewPagerActivity::class.java)
            intent.putExtra("WsData", userData)
            startActivity(intent)
        }
    }

    // ไว้หาวิธีแยกไฟล์
    private fun getProdtResp() {
        val call = RetrofitClient.retrofitClient?.create(ApiInterface::class.java)?.getProduct()

        var recyclerAdapter = RvAdapter(this)
        binding.rvProdt.layoutManager = LinearLayoutManager(this)
        binding.rvProdt.adapter = recyclerAdapter

        recyclerAdapter.setOnItemClickListener(object : RvAdapter.onItemClickListener {
            override fun onItemClick(prodtid: String, tvProdtName: String/*position: Int*/) {
                AlertDialog.Builder(this@HomeActivity)
                    .setTitle("Product Id : $prodtid")
                    .setMessage("Product Name : $tvProdtName")
                    .setPositiveButton("Ok") { _, _ ->
                        finish()
                    }
                    .setNegativeButton("Cancel") { dialogInterface, _ ->
                        dialogInterface.cancel()
                    }
                    .show()
            }

        })

        call?.enqueue(object : Callback<ProductModel> {
            override fun onResponse(call: Call<ProductModel>, response: Response<ProductModel>) {
                if (response.isSuccessful) {
                    response.body()?.entries?.let { recyclerAdapter.setProdtListItems(it) }
                    Toast.makeText(applicationContext, "Get Prduct Success", Toast.LENGTH_LONG)
                        .show()
                }
            }

            override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })

    }
}

/*        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val fragment = HomeFragment()
        val args = Bundle()
        args.putParcelable("FragUserData", userData) //ค่าส่งได้ แต่ Fragment รับค่าไปใช้ไม่ได้
        args.putString("message", "Hi")
        fragment.arguments = args
        mFragmentTransaction.replace(R.id.myFragment, fragment).commit()*/

/*        if (userData != null) {
            fragGetData(userData)
        }*/

/*    fun fragGetData(userData: UserModel): Fragment {
        val fragment = HomeFragment()
        val args = Bundle()
        args.putParcelable("FragUserData", userData) //ค่าส่งได้ แต่ไม่เข้า Fragment (หาทางแก้ต่อไป)
        args.putString("string", "Hi")
        fragment.arguments = args
        return fragment
    }*/

