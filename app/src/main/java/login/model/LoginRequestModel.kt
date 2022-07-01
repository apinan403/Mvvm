package login.model

import base.BaseDataModel

data class LoginRequestModel(
    var username: String,
    var password: String,
) : BaseDataModel()

data class TokenModel(
    val token:String
) : BaseDataModel()
