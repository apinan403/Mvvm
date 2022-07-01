package login.model

import android.os.Parcelable
import base.BaseDataModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    var status: Int,
    var message: String,
    @SerializedName("entries") var entries: Entries
) : Parcelable, BaseDataModel()

@Parcelize
data class Entries (
    @SerializedName("user") var user: User,
    @SerializedName("allowModule") var allowModule:  List<String>,
    @SerializedName("webService") var webService:  List<WebService>,
    var token: String
) : Parcelable, BaseDataModel()

//BaseDataModel()
@Parcelize
data class User (
    var uniqueID: Int,
    var name: String,
    var role: String
) : Parcelable, BaseDataModel()

@Parcelize
data class WebService (
    var key: String,
    var url: String
): Parcelable, BaseDataModel()



