package pl.gregnote.testyourowncv.di.module

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: AppCompatActivity) {

    @Provides
    fun provideActivity(): AppCompatActivity {
        return activity
    }
}
