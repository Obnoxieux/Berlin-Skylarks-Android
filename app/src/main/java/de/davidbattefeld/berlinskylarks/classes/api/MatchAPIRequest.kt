package de.davidbattefeld.berlinskylarks.classes.api

import model.Game

/**
 * Gets games limited to our own club.
 */
class MatchAPIRequest: BSMAPIRequest() {
    suspend fun loadGamesForClub(
        season: Int?,
        gamedays: String?,
    ): List<Game> {
        return apiCall<List<Game>>(
            resource = "clubs/$CLUB_ID/matches.json",
            queryParameters = mutableListOf(
                SEASON_FILTER to (season ?: DEFAULT_SEASON).toString(),
                GAMEDAY_FILTER to (gamedays ?: "current"),
            )
        )
    }

    /**
     * Gets games in general, will also include non-Skylarks games without a search parameter.
     * Only checks for games in DBV and BSVBB!
     *
     * Example request: https://bsm.baseball-softball.de/matches.json?api_key=REDACTED&search=skylarks&filter%5Bseasons%5D%5B%5D=2024&filter%5Bgamedays%5D%5B%5D=next
     */
    suspend fun loadAllGames(
        season: Int?,
        gamedays: String?,
        search: String? = null,
    ): List<Game> {
        val queryParameters = mutableListOf(
            SEASON_FILTER to (season ?: DEFAULT_SEASON).toString(),
            GAMEDAY_FILTER to (gamedays ?: "current"),

            // without these additional queries it returns all leagues in Germany
            ORGANIZATION_FILTER to "federation_$DBV_ORGANIZATION_ID",
            ORGANIZATION_FILTER to "federation_$BSVBB_ORGANIZATION_ID"
        )

        if (!search.isNullOrEmpty()) {
            queryParameters.add(Pair(TEAM_SEARCH, search))
        }

        return apiCall<List<Game>>(
            resource = "matches.json",
            queryParameters = queryParameters,
        )
    }
}