package login.model

import ApiInterface
import IMessageCallback
import RetrofitClient
import android.util.Log
import base.BaseDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



object LoginRepository {

    fun responseData(data: BaseDataModel, callback: IMessageCallback) {
        val call = RetrofitClient.retrofitClient?.create(ApiInterface::class.java)
            ?.post(data)

        call?.enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onSuccess(it) }
                    Log.d("loginRes", "Post Response : " + response.body())
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Log.d("loginRes", t.message.toString())
                callback.onFailed(t.message ?: "")
            }

        })
    }
}