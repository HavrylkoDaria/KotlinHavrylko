import com.squareup.moshi.Json

data class UserResponse(
    @Json(name = "results") val results: List<User>
)

data class User(
    @Json(name = "name") val name: Name,
    @Json(name = "email") val email: String,
    @Json(name = "picture") val picture: Picture
)

data class Name(
    @Json(name = "first") val firstName: String,
    @Json(name = "last") val lastName: String
)

data class Picture(
    @Json(name = "large") val large: String
)
