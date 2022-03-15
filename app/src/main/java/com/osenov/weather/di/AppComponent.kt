package com.osenov.weather.di

import android.content.Context
import com.osenov.weather.WeatherApp
import com.osenov.weather.ui.main.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun injectTo(application: WeatherApp)

    fun viewModelsFactory(): ViewModelFactory

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun context(context: Context) : Builder

        @BindsInstance
        fun application(application: WeatherApp) : Builder

        fun build() : AppComponent
    }

}
