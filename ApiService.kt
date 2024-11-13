import retrofit2.http.GET
import retrofit2.Call

interface ApiService {
    @GET("api/")
    fun getRandomUser(): Call<UserResponse>
}
