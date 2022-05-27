package uz.crud.roomwithmvvm.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import uz.crud.domain.use_case.GetUsers
import javax.inject.Inject

class UsersViewModel(
    private val getUsers: GetUsers
) : ViewModel() {

    val users = flow {
        emit(getUsers.invoke())
    }.shareIn(scope = viewModelScope, started = SharingStarted.Lazily, replay = 1)

//    val favoriteUser = flow {
//        emit(getFavoriteUser.invoke())
//    }.shareIn(scope = viewModelScope, started = SharingStarted.Lazily, replay = 1)

    class UserViewModelFactory @Inject constructor(
        private val getUsers: GetUsers,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UsersViewModel(getUsers) as T
        }
    }
}