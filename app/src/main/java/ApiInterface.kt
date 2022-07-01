
import base.BaseDataModel
import home.model.ProductModel
import login.model.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {

//    @GET("AppStoreSystem/files/project/arra_new/1_Authentication/ASaleLogin/login.json")
//    fun getUser(): Call<UserModel>

    @GET("AppStoreSystem/files/project/svoa_online/new/2_Home/ProductList/product.json")
    fun getProduct(): Call<ProductModel>

    @Headers("Content-Type: application/json")
    @POST("AppStoreSystem/files/project/arra_new/1_Authentication/ASaleLogin/login.json")
    fun post(@Body userLogin: BaseDataModel): Call<UserModel> /*Observable<UserModel>*/

}
