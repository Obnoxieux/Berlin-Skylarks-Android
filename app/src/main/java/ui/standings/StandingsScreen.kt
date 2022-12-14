package ui.standings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.davidbattefeld.berlinskylarks.ui.theme.BerlinSkylarksTheme

@Composable
fun StandingsScreen() {
    Text(text = "Standings")
}

@Preview
@Composable
fun StandingsScreenPreview() {
    BerlinSkylarksTheme {
        StandingsScreen()
    }
}