package com.bakharaalief.hulaapp.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bakharaalief.hulaapp.R
import com.bakharaalief.hulaapp.core.data.Resource
import com.bakharaalief.hulaapp.core.ui.MovieListAdapter
import com.bakharaalief.hulaapp.core.ui.ViewModelFactory
import com.bakharaalief.hulaapp.databinding.ActivityMainBinding
import com.bakharaalief.hulaapp.favorite.FavoriteActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MovieListAdapter

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
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    private fun setRv() {
        adapter = MovieListAdapter()
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
                toFavoriteMovies()
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

    private fun toFavoriteMovies() {
        val intent = Intent(this, FavoriteActivity::class.java)
        startActivity(intent)
    }
}