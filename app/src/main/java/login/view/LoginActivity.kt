package login.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mvvm.databinding.ActivityLoginBinding
import home.view.HomeActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*
import login.model.LoginRequestModel
import login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val loginViewModel = LoginViewModel() /*New OBJ*/
    lateinit var cacheUsername : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        cacheUsername = getSharedPreferences("Test" , Context.MODE_PRIVATE)

        binding.txtUsername.setText(cacheUsername.getString("Username", String()))

        loginViewModel.userData.observe(this@LoginActivity, Observer {
            intent.putExtra("UserData", it)
            startActivity(intent)
        })

        loginViewModel.errorMsg.observe(this@LoginActivity, Observer {
            Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_SHORT).show()
        })

        binding.btnLogin.setOnClickListener {
            val dataLogin = LoginRequestModel(
                username = binding.txtUsername.text.toString(),
                password = binding.txtPass.text.toString()
            )

            if (loginViewModel.vaLidateLogin(dataLogin).first) {
                loginViewModel.login(binding.txtUsername.text.toString(), binding.txtPass.text.toString())
                Toast.makeText(this@LoginActivity, loginViewModel.vaLidateLogin(dataLogin).second, Toast.LENGTH_SHORT).show()

                if (binding.swRemember.isChecked) {
                    var cachingUsername = cacheUsername.edit()
                    cachingUsername.putString("Username", binding.txtUsername.text.toString())
                    Toast.makeText(this@LoginActivity, "Store Cache", Toast.LENGTH_SHORT).show()
                    cachingUsername.apply()
                }
                else cacheUsername.edit().clear().apply()
            }
            else Toast.makeText(applicationContext, loginViewModel.vaLidateLogin(dataLogin).second, Toast.LENGTH_SHORT).show()
        }

    }

}

//        binding.textForgetpass.text = "Forget password?"
//        binding.textVirsion.text = "Version ASALE DEV 1.14.1"

//            intent.putExtra("UniqueId", "${it.entries.user.uniqueID}")
//            intent.putExtra("Name", "${it.entries.user.name}")
//            intent.putExtra("Role", "${it.entries.user.role}")
//            intent.putExtra("UserDataOB", "$it")

/*            loginViewModel.validLogin(binding.txtUsername.text.toString(), binding.txtPass.text.toString(),
                object : IMessageCallback {
                    override fun onSuccess(data: Any) {

                        loginViewModel.login(binding.txtUsername.text.toString(), binding.txtPass.text.toString())

                        Toast.makeText(applicationContext, data.toString(), Toast.LENGTH_LONG).show()
                    }

                    override fun onFailed(msg: String) {
                        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
                    }

                })*/