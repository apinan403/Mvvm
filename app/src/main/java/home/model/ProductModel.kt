package home.model

import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("statusCode")
    var statusCode: Int,
    @SerializedName("message")
    var message: String,
    @SerializedName("entries")
    val entries: List<Entry>
)

data class Entry(
    @SerializedName("prodID")
    val prodID: String,
    @SerializedName("productName")
    val productName: String,
    @SerializedName("images")
    val images: List<String>
)
