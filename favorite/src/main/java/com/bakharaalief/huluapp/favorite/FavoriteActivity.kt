package com.bakharaalief.huluapp.favorite

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bakharaalief.hulaapp.detail.DetailActivity
import com.bakharaalief.hulaapp.di.FavoriteModuleDependencies
import com.bakharaalief.huluapp.core.domain.model.Movie
import com.bakharaalief.huluapp.core.ui.MovieListAdapter
import com.bakharaalief.huluapp.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: MovieListAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

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
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
    }

    private fun setRv() {
        adapter = MovieListAdapter {
            toDetail(it)
        }
        binding.movieRv.layoutManager = GridLayoutManager(this, 2)
        binding.movieRv.adapter = adapter
    }

    private fun getListMovie() {
        viewModel.getFavoriteMovies.observe(this) { movies ->
            if (movies != null) {
                if (movies.isEmpty()) setEmptyText(true) else setEmptyText(false)
                adapter.submitList(movies)
            }
        }
    }

    private fun toDetail(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, movie)
        startActivity(intent)
    }

    private fun setEmptyText(status: Boolean) {
        binding.emptyText.visibility = if (status) View.VISIBLE else View.GONE
    }
}