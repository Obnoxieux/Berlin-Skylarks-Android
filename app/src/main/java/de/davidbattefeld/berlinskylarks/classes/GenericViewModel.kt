package de.davidbattefeld.berlinskylarks.classes

import android.app.Application
import android.icu.util.Calendar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import de.davidbattefeld.berlinskylarks.global.readInt
import de.davidbattefeld.berlinskylarks.global.writeInt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class GenericViewModel(application: Application) : AndroidViewModel(application) {
    open var url = ""

    var selectedSeason by mutableStateOf(Calendar.getInstance().get(Calendar.YEAR))

    val api = API()

    // To be overridden by each specific view model
    open fun load() {

    }

    init {
        readSelectedSeason()
    }

    fun readSelectedSeason() {
        val context = getApplication<Application>().applicationContext
        viewModelScope.launch(Dispatchers.IO) {
            val result = context.readInt("season").first()
            withContext(Dispatchers.Main) {
                selectedSeason = result
            }
        }
    }
    fun writeSelectedSeason(season: Int) {
        val context = getApplication<Application>().applicationContext
        viewModelScope.launch(Dispatchers.IO) {
            context.writeInt("season", season)
        }
    }
}