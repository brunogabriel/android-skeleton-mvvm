package io.github.brunogabriel.photolist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import io.github.brunogabriel.core.recyclerview.MarginItemDecoration
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
    private val photoAdapter: PhotoAdapter by inject()
    private lateinit var binding: ActivityPhotoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_list)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setupView()
    }

    private fun setupView() {
        setupToolbar()
        setupViewModel()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupViewModel() {
        viewModel.fetchPhotos()
        viewModel.state.observe(this, Observer { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    binding.loadingView.visibility = View.GONE
                    binding.tryAgainView.visibility = View.GONE
                    binding.photosRecyclerView.visibility = View.VISIBLE
                    photoAdapter.photos = uiState.data
                }

                is UiState.Loading -> {
                    binding.loadingView.visibility = View.VISIBLE
                }

                is UiState.Failed -> {
                    binding.loadingView.visibility = View.GONE
                    binding.tryAgainView.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.photosRecyclerView.apply {
            adapter = photoAdapter
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.margin_span_size),
                    (this.layoutManager as GridLayoutManager).spanCount
                )
            )
        }
    }
}