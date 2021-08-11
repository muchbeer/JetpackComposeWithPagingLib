package raum.muchbeer.jetpackcomposewithpaginglib.component

import android.util.Log
import androidx.navigation.NavController
import raum.muchbeer.jetpackcomposewithpaginglib.component.NavGraph.Destinations.AddStudent

class NavGraph(navController: NavController) {

    val addStudent: () -> Unit = {
        navController.navigate(AddStudent)
        Log.d("AddView", "ViewStuff")

    }

    object Destinations {
        const val Home = "home"
        const val AddStudent = "addStudent"
    }
}