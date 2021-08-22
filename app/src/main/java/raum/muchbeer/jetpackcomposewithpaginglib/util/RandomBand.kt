package raum.muchbeer.jetpackcomposewithpaginglib.util

import raum.muchbeer.jetpackcomposewithpaginglib.model.BandModel
import raum.muchbeer.jetpackcomposewithpaginglib.model.TodoIcon

fun generateRandomTodoItem(): BandModel {
    val message = listOf(
        "Learn compose",
        "Learn state",
        "Build dynamic UIs",
        "Learn Unidirectional Data Flow",
        "Integrate LiveData",
        "Integrate ViewModel",
        "Remember to savedState!",
        "Build stateless composables",
        "Use state from stateless composables"
    ).random()
    val icon = TodoIcon.values().random()
    return BandModel(message, icon)
}