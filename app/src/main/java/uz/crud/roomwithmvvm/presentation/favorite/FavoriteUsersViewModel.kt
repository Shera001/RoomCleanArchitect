package uz.crud.roomwithmvvm.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import uz.crud.domain.model.User
import uz.crud.domain.use_case.GetFavoriteUser
import javax.inject.Inject

class FavoriteUsersViewModel(
    private val getFavoriteUser: GetFavoriteUser
) : ViewModel() {

    private val _users = MutableSharedFlow<List<User>>()
    val users: SharedFlow<List<User>> get() = _users.asSharedFlow()

    fun getFavoriteUsers() {
        viewModelScope.launch {
            _users.emitAll(getFavoriteUser.invoke())
        }
    }

    class FavoriteUsersViewModelFactory @Inject constructor(
        private val getFavoriteUser: GetFavoriteUser
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoriteUsersViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FavoriteUsersViewModel(getFavoriteUser) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}