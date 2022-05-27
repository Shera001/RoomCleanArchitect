package uz.crud.roomwithmvvm.app

import android.app.Application
import uz.crud.roomwithmvvm.di.AppComponent
import uz.crud.roomwithmvvm.di.DaggerAppComponent

class MainApp: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(context = this)
            .build()
    }
}