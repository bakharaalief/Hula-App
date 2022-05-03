package com.bakharaalief.hulaapp.favorite

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bakharaalief.hulaapp.core.ui.MovieListAdapter
import com.bakharaalief.hulaapp.databinding.ActivityFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionBar()
        setViewModel()
        setRv()
        getListMovie()
    }

    private fun setActionBar() {
        setSupportActionBar(binding.homeToolbar)
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

    private fun setViewModel() {
        viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
    }

    private fun setRv() {
        adapter = MovieListAdapter()
        binding.movieRv.layoutManager = GridLayoutManager(this, 2)
        binding.movieRv.adapter = adapter
    }

    private fun getListMovie() {
        viewModel.getFavoriteMovies.observe(this) { movies ->
            if (movies != null) {
                adapter.submitList(movies)
            }
        }
    }
}