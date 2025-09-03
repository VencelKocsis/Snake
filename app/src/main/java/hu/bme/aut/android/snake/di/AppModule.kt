package hu.bme.aut.android.snake.di

import android.content.Context
import android.hardware.SensorManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.snake.data.PreferenceStorage
import hu.bme.aut.android.snake.data.dao.TopScoreDao
import hu.bme.aut.android.snake.data.database.TopScoreDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): TopScoreDatabase {
        return TopScoreDatabase.getDatabase(appContext)
    }

    @Provides
    @Singleton
    fun provideTopScoreDao(database: TopScoreDatabase): TopScoreDao {
        return database.topScoresDao()
    }

    @Singleton
    @Provides
    fun providePreferenceStorage(@ApplicationContext context: Context): PreferenceStorage {
        return PreferenceStorage(context)
    }

    @Provides
    fun provideSensorManager(@ApplicationContext context: Context): SensorManager {
        return context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
}