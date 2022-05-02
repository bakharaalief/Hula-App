package com.bakharaalief.hulaapp.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.bakharaalief.hulaapp.R
import com.bakharaalief.hulaapp.core.data.Resource
import com.bakharaalief.hulaapp.core.ui.ViewModelFactory
import com.bakharaalief.hulaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionBar()
        setViewModel()
        getListMovie()
    }

    private fun setActionBar() {
        setSupportActionBar(binding.homeToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
//                searchUser(query)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return true
    }

    private fun setViewModel() {
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    private fun getListMovie() {
        viewModel.movies.observe(this) { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> setLoading(true)
                    is Resource.Success -> {
                        setLoading(false)
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
}