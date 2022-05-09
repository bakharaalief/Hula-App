package com.bakharaalief.hulaapp.detail

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bakharaalief.hulaapp.R
import com.bakharaalief.hulaapp.databinding.ActivityDetailBinding
import com.bakharaalief.huluapp.core.domain.model.Movie
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var movie: Movie
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get data from intent
        movie = intent.getParcelableExtra<Movie>(EXTRA_DATA) as Movie

        setActionBar()
        setInformation()
        setBookmarkIcon()
        setViewModel()

        binding.bookmarkButton.setOnClickListener {
            isFavorite = if (isFavorite) {
                viewModel.setMovieFavorite(movie, false)
                Toast.makeText(this, getString(R.string.success_to_remove_bookmark), Toast.LENGTH_SHORT).show()
                false
            } else {
                viewModel.setMovieFavorite(movie, true)
                Toast.makeText(this, getString(R.string.success_to_bookmark), Toast.LENGTH_SHORT).show()
                true
            }

            val image =
                if (isFavorite) R.drawable.ic_baseline_bookmark_24 else R.drawable.ic_baseline_bookmark_border_24
            binding.bookmarkButton.setImageResource(image)
        }
    }

    private fun setActionBar() {
        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.onBackPressed()
                true
            }
            else -> true
        }
    }

    private fun setInformation() {
        binding.topAppBar.title = movie.originalTitle

        Glide
            .with(this)
            .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(binding.movieCoverActionbar)

        binding.movieOverviewOriginalTitle.text = movie.originalTitle
        binding.movieLanguageDetail.text = movie.originalLanguage
        binding.movieReleaseDetail.text = movie.releaseDate
        binding.movieOverviewDetail.text = movie.overview
        binding.movieRatingDetail.text = movie.voteAverage.toString()
    }

    private fun setBookmarkIcon() {
        isFavorite = movie.isFavorite
        val image =
            if (isFavorite) R.drawable.ic_baseline_bookmark_24 else R.drawable.ic_baseline_bookmark_border_24
        binding.bookmarkButton.setImageResource(image)
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
    }

    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }
}