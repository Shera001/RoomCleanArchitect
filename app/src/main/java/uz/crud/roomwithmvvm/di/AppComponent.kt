package uz.crud.roomwithmvvm.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import uz.crud.roomwithmvvm.presentation.favorite.FavoriteUsersFragment
import uz.crud.roomwithmvvm.presentation.user.UsersFragment

@Component(modules = [DataModule::class, DomainModule::class])
interface AppComponent {

    fun inject(fragment: UsersFragment)
    fun inject(fragment: FavoriteUsersFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(application: Application): Builder

        fun build(): AppComponent
    }
}