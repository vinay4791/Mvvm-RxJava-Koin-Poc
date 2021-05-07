package com.example.rxjavakoinpoc.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.rxjavakoinpoc.R
import com.example.rxjavakoinpoc.base.BaseFragment
import com.example.rxjavakoinpoc.databinding.FragmentDetailBinding
import com.example.rxjavakoinpoc.movies.viewstate.MoviesInfo

class DetailsFragment : BaseFragment() {

    private lateinit var dataBinding: FragmentDetailBinding
    var movie: MoviesInfo? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            movie = DetailsFragmentArgs.fromBundle(it).movieDetail
        }

     //   (activity as AppCompatActivity?)!!.supportActionBar!!.title = movie?.title

        dataBinding.movie = movie
    }


}