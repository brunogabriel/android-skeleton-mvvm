package io.github.brunogabriel.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Created by bruno on 2020-01-03
 */
fun ViewGroup.inflate(@LayoutRes layoutResource: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutResource, this, attachToRoot)
}

