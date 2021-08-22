package raum.muchbeer.jetpackcomposewithpaginglib.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey
import raum.muchbeer.jetpackcomposewithpaginglib.R

data class BandModel(
    val bandname : String,
    val icon : TodoIcon,
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L
)
enum class TodoIcon(val imageVector: ImageVector, @StringRes val contentDescription: Int) {
    Square(Icons.Default.CropSquare, R.string.cd_crop_square),
    Done(Icons.Default.Done, R.string.cd_done),
    Event(Icons.Default.Event, R.string.cd_event),
    Privacy(Icons.Default.PrivacyTip, R.string.cd_privacy),
    Trash(Icons.Filled.RestoreFromTrash, R.string.cd_restore);

    companion object {
        val Default = Square

    }
}

@Entity(tableName = "live_band_table")
data class SchoolModel(
    val schoolname : String,
    val icon : Int,
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L
) {
    fun converticonToLocalIcon() : LocalIcon {
       return getLocalIcon(icon)!!
    }
}

enum class LocalIcon(@DrawableRes val imageVector: Int, val description: String) {
    Square(R.drawable.ic_crop_square, "Crop Square"),
    Done(R.drawable.ic_done, "Done"),
    Event(R.drawable.ic_event, "Event"),
    Privacy(R.drawable.ic_privacy, "Privacy"),
    Trash(R.drawable.ic_restore_from_trash, "Restore From Trash");

    companion object {   val Default = Square  }
}

fun getAllIcons(): List<LocalIcon>{
    return listOf(
        LocalIcon.Square, LocalIcon.Done, LocalIcon.Event, LocalIcon.Privacy, LocalIcon.Trash
    )
}
fun getLocalIcon(iconSelected: Int): LocalIcon? {
    val map = LocalIcon.values().associateBy(LocalIcon::imageVector)
    return map[iconSelected]
}