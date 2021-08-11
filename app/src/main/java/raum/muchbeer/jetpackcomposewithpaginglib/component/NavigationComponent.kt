package raum.muchbeer.jetpackcomposewithpaginglib.component

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import raum.muchbeer.jetpackcomposewithpaginglib.component.addstudent.AddView
import raum.muchbeer.jetpackcomposewithpaginglib.component.homepage.HomeView


@ExperimentalMaterialApi
@Composable
fun NavigationComponent() {
    val navController = rememberNavController()

  //  NavHost(navController = , graph = )
 //   HandsOnKotlinTheme {

        NavHost(navController = navController, startDestination = NavGraph.Destinations.Home) {
            composable(NavGraph.Destinations.Home) { HomeView(navController) }
            composable(NavGraph.Destinations.AddStudent) { AddView(navController) }
        }

}