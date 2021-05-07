package com.example.rxjavakoinpoc.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavakoinpoc.R
import com.example.rxjavakoinpoc.databinding.MoviesItemBinding
import com.example.rxjavakoinpoc.movies.viewstate.MoviesInfo

class MoviesListAdapter : RecyclerView.Adapter<MoviesViewHolder>() {

    private var moviesList : List<MoviesInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<MoviesItemBinding>(layoutInflater, R.layout.movies_item, parent, false)

        return MoviesViewHolder(view)
    }

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    fun setItems(moviesList : List<MoviesInfo>){
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

}