package uz.crud.domain.util

sealed class Resource {
    object Success : Resource()
    sealed class Empty : Resource() {
        object NameEmpty : Empty()
        object NumberEmpty : Empty()
    }
}