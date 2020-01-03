package io.github.brunogabriel.photolist.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.core.extensions.inflate
import io.github.brunogabriel.domain.entities.Photo
import io.github.brunogabriel.photolist.R

/**
 * Created by bruno on 2020-01-03
 */
class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    var photos: List<Photo> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.holder_photo))
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: Photo) = with(itemView) {
            // TODO: apply picasso data
        }
    }
}