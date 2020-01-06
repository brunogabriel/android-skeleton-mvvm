package io.github.brunogabriel.core.extensions

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

/**
 * Created by bruno on 2020-01-03
 */
fun ImageView.loadImage(url: String) {
    Picasso.get()
        .load(url)
        .networkPolicy(NetworkPolicy.OFFLINE)
        .into(this, object : Callback {
            override fun onSuccess() {
            }

            override fun onError(e: Exception?) {
                Picasso.get().load(url).into(this@loadImage)
            }
        })
}