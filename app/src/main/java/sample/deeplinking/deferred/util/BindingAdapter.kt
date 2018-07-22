package sample.deeplinking.deferred.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide


@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view).load(url).into(view)
}
