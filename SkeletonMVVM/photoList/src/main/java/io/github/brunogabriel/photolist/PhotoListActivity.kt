package io.github.brunogabriel.photolist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import io.github.brunogabriel.core.ui.UiState
import io.github.brunogabriel.photolist.adapter.PhotoAdapter
import io.github.brunogabriel.photolist.databinding.ActivityPhotoListBinding
import io.github.brunogabriel.photolist.viewmodel.PhotoListViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by bruno on 2020-01-02
 */
class PhotoListActivity : AppCompatActivity() {

    private val viewModel: PhotoListViewModel by viewModel()
    private val adapter: PhotoAdapter by inject()
    private lateinit var binding: ActivityPhotoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_list)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setupView()
    }

    private fun setupView() {
        setSupportActionBar(binding.toolbar)
        viewModel.fetchPhotos()
        viewModel.state.observe(this, Observer { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    binding.loadingView.visibility = View.GONE
                    binding.photosRecyclerView.visibility = View.VISIBLE
                    adapter.photos = uiState.data
                }

                is UiState.Loading -> {
                    binding.loadingView.visibility = View.VISIBLE
                }

                is UiState.Failed -> {
                    binding.loadingView.visibility = View.GONE
                }
            }
        })
        binding.photosRecyclerView.adapter = adapter
    }
}