package hu.bme.aut.android.snake.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class PreferenceStorage(private val context: Context) {
    companion object {
        private val SENSOR_CONTROL_KEY = booleanPreferencesKey("sensor_control")
    }

    val isSensorControlled: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[SENSOR_CONTROL_KEY] ?: false
        }

    suspend fun setSensorControlled(isSensorControlled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SENSOR_CONTROL_KEY] = isSensorControlled
        }
    }
}