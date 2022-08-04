package uz.crud.roomwithmvvm.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.crud.domain.model.User
import uz.crud.domain.use_case.AddUser
import uz.crud.domain.use_case.GetUsers
import uz.crud.domain.use_case.UpdateUser
import uz.crud.domain.util.Resource
import javax.inject.Inject

class UsersViewModel(
    private val getUsers: GetUsers,
    private val addUser: AddUser,
    private val updateUser: UpdateUser
) : ViewModel() {

    private val _users = MutableSharedFlow<List<User>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val users: SharedFlow<List<User>> = _users.asSharedFlow()

    private val _result = MutableSharedFlow<Resource>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val result: SharedFlow<Resource> = _result.asSharedFlow()

    fun getUsers() {
        viewModelScope.launch {
            _users.emitAll(getUsers.invoke())
        }
    }

    fun addUser(user: User, isUpdate: Boolean) {
        viewModelScope.launch {
            _result.emit(addUser.invoke(user, isUpdate))
        }
    }

    fun updateItem(user: User) {
        viewModelScope.launch {
            updateUser.invoke(user)
        }
    }

    class UserViewModelFactory @Inject constructor(
        private val getUsers: GetUsers,
        private val addUser: AddUser,
        private val updateUser: UpdateUser
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UsersViewModel(getUsers, addUser, updateUser) as T
        }
    }
}