package com.example.rxjavakoinpoc.movies.adapter

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavakoinpoc.databinding.MoviesItemBinding
import com.example.rxjavakoinpoc.movies.MoviesListFragmentDirections
import com.example.rxjavakoinpoc.movies.viewstate.MoviesInfo

class MoviesViewHolder(var view: MoviesItemBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(movie: MoviesInfo) {
        view.movie = movie
        view.movieLayout.setOnClickListener {
            movie.let {
                val action = MoviesListFragmentDirections.actionDetail(it)
                Navigation.findNavController(view.movieLayout).navigate(action)
            }
        }
    }
}
