package login.viewmodel

import IMessageCallback
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import login.model.*

class LoginViewModel() : ViewModel() {

    private val loginRes = LoginRepository

    val userData: MutableLiveData<UserModel> by lazy {
        MutableLiveData<UserModel>()
    }

    val errorMsg: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun vaLidateLogin(loginData: LoginRequestModel) : Pair<Boolean, String> {
        when {
            loginData.username.isNullOrEmpty() -> return Pair(false, "Please Enter Username!!")
            loginData.username.length < 5 -> return Pair(false, "Username must be minimum 5 characters")

            loginData.password.isNullOrEmpty() -> return Pair(false, "Please Enter Password!!")
            loginData.password.length < 8 -> return Pair(false, "Password must be minimum 8 characters")
        }
        return Pair(true, "Login Success")
    }

    fun login(username: String, pass: String) {

        var user = Gson().toJson(TokenModel(username))
        var user1 = Gson().toJson(LoginRequestModel(username, pass))
        Log.i("GsonConvert", "UserModel To Json : $user")
        Log.i("GsonConvert", "UserRequestModel To Json : $user1")

        val str = "{\"token\":\"abcdefgh\"}"
        val tokenModel: TokenModel = Gson().fromJson(str, TokenModel::class.java)
        Log.i("GsonConvert", "Token : ${tokenModel.token}")

        loginRes.responseData(LoginRequestModel(username, pass), object : IMessageCallback {
            override fun onFailed(msg: String) {
                errorMsg.postValue(msg)
            }

            override fun onSuccess(data: Any) {
                userData.postValue(data as UserModel?)
            }
        })
    }

}

/*    fun validLogin(username: String, pass: String, validCallBack: IMessageCallback) {
        if (username.isEmpty()){
            validCallBack.onFailed("Please Enter Username!!")
        }else if (username.length < 5){
            validCallBack.onFailed("Username must be minimum 5 characters")
        }
        else if (pass.isEmpty()){
            validCallBack.onFailed("Please Enter Password!!")
        }
        else if (pass.length < 8) {
            validCallBack.onFailed("Password must be minimum 8 characters")
        } else {
            validCallBack.onSuccess("Login Success")
        }
    }*/
