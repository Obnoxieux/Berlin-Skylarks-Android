package de.davidbattefeld.berlinskylarks.classes

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import de.davidbattefeld.berlinskylarks.global.readInt
import de.davidbattefeld.berlinskylarks.global.writeInt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import model.GameScore

class ScoresViewModel(application: Application) : GenericViewModel(application) {
    var gameScores = mutableStateListOf<GameScore>()
    var gamesCount by mutableStateOf(0)

    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")

    override fun load() {
        readSelectedSeason()

        gameScores.addAll(api.loadGamesForClub(selectedSeason, "any"))
        gameScores.forEach { gameScore ->
            gameScore.addDate()
            gameScore.determineGameStatus()
        }
    }

    /*fun loadGames() = runBlocking {
        launch {
            var json: String
            val type = object : TypeToken<List<GameScore>>() {}.type
            withContext(Dispatchers.IO) { json = fetchJSONData() }
            val results: List<GameScore> = parseResponse<List<GameScore>>(json = json, typeToken = type)
            results.forEach { result ->
                result.addDate()
                result.determineGameStatus()
            }
            gameScores.addAll(results)
            gamesCount = gameScores.count()
            //teeeeeest()
        }
    }*/

    // TODO: test if it's possible to make this generic

    var selectedSeason by mutableStateOf(
        2022
        //Calendar.getInstance().get(Calendar.YEAR)
    )

    fun readSelectedSeason() {
        val context = getApplication<Application>().applicationContext
        viewModelScope.launch(Dispatchers.IO) {
            selectedSeason = context.readInt("season").first()
        }
    }
    fun writeSelectedSeason(season: Int) {
        val context = getApplication<Application>().applicationContext
        viewModelScope.launch(Dispatchers.IO) {
            context.writeInt("season", season)
        }
    }
}