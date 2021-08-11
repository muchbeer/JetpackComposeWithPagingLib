package raum.muchbeer.jetpackcomposewithpaginglib.component

import androidx.navigation.NavController
import raum.muchbeer.jetpackcomposewithpaginglib.component.NavGraph.Destinations.AddStudent

class NavGraph(navController: NavController) {
    val addStudent: () -> Unit = {
        navController.navigate(AddStudent)
    }

    object Destinations {
        const val Home = "home"
        const val AddStudent = "addStudent"
    }
}