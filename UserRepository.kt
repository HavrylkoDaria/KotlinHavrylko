import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val apiService: ApiService) {
    fun fetchRandomUser(onResult: (User?) -> Unit) {
        apiService.getRandomUser().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    onResult(response.body()?.results?.firstOrNull())
                } else {
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }
}
