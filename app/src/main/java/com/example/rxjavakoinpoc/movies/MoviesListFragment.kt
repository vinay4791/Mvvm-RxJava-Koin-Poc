package com.example.rxjavakoinpoc.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavakoinpoc.R
import com.example.rxjavakoinpoc.base.BaseFragment
import com.example.rxjavakoinpoc.movies.adapter.MoviesListAdapter
import com.example.rxjavakoinpoc.movies.viewstate.MoviesInfo
import com.example.rxjavakoinpoc.movies.viewstate.MoviesListViewState
import com.example.rxjavakoinpoc.util.Constants.API_KEY
import com.example.rxjavakoinpoc.util.Constants.DEFAULT_LANGUAGE
import com.example.rxjavakoinpoc.util.Constants.INITIAL_PAGE
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject

class MoviesListFragment : BaseFragment() {

    private val viewModel: MoviesListViewModel by viewModel()
    private val moviesListAdapter: MoviesListAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        observeMoviesListData()
        fetchMoviesListData()
    }

    private fun fetchMoviesListData() {
        viewModel.fetchMoviesList(API_KEY, DEFAULT_LANGUAGE, INITIAL_PAGE)
    }

    private fun observeMoviesListData() {
        viewModel.moviesListData().observe(viewLifecycleOwner, Observer { viewState ->
            when(viewState){
                is MoviesListViewState.Loading -> showApiLoadingIndicator()
                is MoviesListViewState.Success -> showMoviesListData(viewState.moviesInfoList)
                is MoviesListViewState.Error -> showError()
            }
        })
    }

    private fun showError() {
        Log.d("vinay","showError")
    }

    private fun showMoviesListData(moviesInfoList: List<MoviesInfo>) {
        moviesList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        moviesList.adapter = moviesListAdapter
        moviesListAdapter.setItems(moviesInfoList)
    }

    private fun showApiLoadingIndicator() {
        Log.d("vinay","showApiLoadingIndicator")
    }

}