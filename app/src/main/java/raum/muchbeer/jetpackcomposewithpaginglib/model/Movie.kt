package raum.muchbeer.jetpackcomposewithpaginglib.model

data class Movie(
    val id: Int,
    val title: String,
    val backdrop_path: String? = null,
    val overview : String,
    val poster_path : String? = null
)
