import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val repository = UserRepository(NetworkModule.apiService)

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    fun loadRandomUser() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchRandomUser { userResult ->
                _user.postValue(userResult)
            }
        }
    }
}
