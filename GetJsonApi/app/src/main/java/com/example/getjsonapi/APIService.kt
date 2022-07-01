package com.example.getjsonapi

import com.example.getjsonapi.nestedJSON.NestedJSONModel
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    /*
   Nested JSON
*/

    @GET("/johncodeos-blog/ParseJSONRetrofitConvertersExample/main/nested.json")
    suspend fun getEmployeesNested(): Response<NestedJSONModel>
}