package uz.crud.roomwithmvvm.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import uz.crud.roomwithmvvm.presentation.user.UsersFragment

@Component(modules = [DataModule::class, DomainModule::class])
interface AppComponent {

    fun inject(fragment: UsersFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}