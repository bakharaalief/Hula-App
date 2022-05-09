package com.bakharaalief.hulaapp.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bakharaalief.hulaapp.R
import com.bakharaalief.hulaapp.databinding.ActivityMainBinding
import com.bakharaalief.hulaapp.detail.DetailActivity
import com.bakharaalief.huluapp.core.data.Resource
import com.bakharaalief.huluapp.core.domain.model.Movie
import com.bakharaalief.huluapp.core.ui.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MovieListAdapter

    private val favoriteClass = "com.bakharaalief.huluapp.favorite.FavoriteActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
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

    private fun setViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun setRv() {
        adapter = MovieListAdapter { toDetail(it) }
        binding.movieRv.layoutManager = GridLayoutManager(this, 2)
        binding.movieRv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bookmark -> {
                toFavorite()
                true
            }
            R.id.setting -> {
                toSetting()
                true
            }
            else -> false
        }
    }

    private fun getListMovie() {
        viewModel.movies.observe(this) { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> setLoading(true)
                    is Resource.Success -> {
                        setLoading(false)
                        adapter.submitList(movies.data)
                    }
                    is Resource.Error -> {
                        setLoading(false)
                        Toast.makeText(this, movies.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setLoading(status: Boolean) {
        binding.loadingIndicator.visibility = if (status) View.VISIBLE else View.GONE
    }

    private fun toFavorite() {
        startActivity(
            Intent(
                this,
                Class.forName(favoriteClass)
            )
        )
    }

    private fun toDetail(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, movie)
        startActivity(intent)
    }

    private fun toSetting() {
        startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
    }

    override fun onBackPressed() {
        finishAfterTransition()
    }
}