package hu.bme.aut.android.snake.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _title = MutableLiveData("Main Menu")
    val title: LiveData<String> = _title

    private val _navigate = MutableLiveData<() -> Unit>()
    val navigate: LiveData<() -> Unit> = _navigate

    private val _showBackArrow = MutableLiveData(false)
    val showBackArrow: LiveData<Boolean> = _showBackArrow

    fun updateTitle(newTitle: String, showBackArrow: Boolean = false) {
        _title.value = newTitle
        _showBackArrow.value = showBackArrow
    }

    fun setNavigationAction(lambda: () -> Unit) {
        _navigate.value = lambda
    }
}