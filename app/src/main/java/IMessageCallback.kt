interface IMessageCallback {
    fun onSuccess(data: Any)
    fun onFailed(msg: String)
}