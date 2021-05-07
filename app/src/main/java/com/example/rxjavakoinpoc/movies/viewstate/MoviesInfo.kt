package com.example.rxjavakoinpoc.movies.viewstate

import android.os.Parcelable
import com.example.rxjavakoinpoc.util.Constants.EMPTY_STRING
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesInfo(
    val title: String,
    val adultRated: Boolean,
    val language: String,
    val date: String,
    val thumbnailImageUrl : String,
    val imageUrl : String,
    val overview: String,
    val popularity: Double

) : Parcelable {
    companion object {
        val EMPTY = MoviesInfo(
            title = EMPTY_STRING,
            adultRated = false,
            language = EMPTY_STRING,
            date = EMPTY_STRING,
            thumbnailImageUrl = EMPTY_STRING,
            imageUrl = EMPTY_STRING,
            overview = EMPTY_STRING,
            popularity = 0.0
        )
    }

}