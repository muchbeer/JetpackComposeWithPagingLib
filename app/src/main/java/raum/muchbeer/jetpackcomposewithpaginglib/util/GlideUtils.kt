package raum.muchbeer.jetpackcomposewithpaginglib.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import raum.muchbeer.jetpackcomposewithpaginglib.R

const val DEFAULT_IMAGE = R.drawable.ic_broken_image

@Composable
fun loadPicture(imageUrl : String, @DrawableRes imageDefault: Int)
        : MutableState<Bitmap?> {


    val imageState : MutableState<Bitmap?> = remember { mutableStateOf(null) }

    // show default image while image loads
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imageDefault)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) { }
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                imageState.value = resource
            }
        })

    // get network image
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imageUrl)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) { }
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                imageState.value = resource
            }
        })

    return imageState
}