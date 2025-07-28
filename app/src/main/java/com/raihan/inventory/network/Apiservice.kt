package com.raihan.inventory.network

import com.raihan.inventory.model.LoginResponse
import com.raihan.inventory.model.ProductResponse
import com.raihan.inventory.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("api/login.php")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("api/register.php")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @GET("api/product.php")
    fun getProducts(): Call<ProductResponse>
}
