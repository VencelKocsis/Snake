package hu.bme.aut.android.snake.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.snake.data.PreferenceStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferenceStorage: PreferenceStorage
) : ViewModel() {

    private val _isSensorControlled = MutableStateFlow(false)
    val isSensorControlled = _isSensorControlled

    init {
        viewModelScope.launch {
            preferenceStorage.isSensorControlled.collect { value ->
                _isSensorControlled.value = value
            }
        }
    }

    fun setSensorControlled(isSensorControlled: Boolean) {
        viewModelScope.launch {
            preferenceStorage.setSensorControlled(isSensorControlled)
        }
    }
}