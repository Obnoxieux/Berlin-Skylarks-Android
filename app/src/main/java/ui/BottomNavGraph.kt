package ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ui.club.ClubScreen
import ui.home.HomeScreen
import ui.scores.ScoresScreen
import ui.settings.SettingsScreen
import ui.standings.StandingsScreen

@Composable
fun BottomNavGraph(modifier: Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Scores.route) {
            ScoresScreen()
        }
        composable(route = BottomBarScreen.Standings.route) {
            StandingsScreen()
        }
        composable(route = BottomBarScreen.Club.route) {
            ClubScreen()
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
    }
}