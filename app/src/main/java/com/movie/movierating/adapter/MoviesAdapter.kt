package com.movie.movierating.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.movierating.databinding.ActivityMainBinding
import com.movie.movierating.databinding.RowMoviesBinding
import com.movie.movierating.model.Movies

class MoviesAdapter(
    private val movies: List<Movies>,
    val context: Context,
    val listener: (Movies) -> Unit): RecyclerView.Adapter<MoviesAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MoviesAdapter.ViewHolder {
        val itemBinding = RowMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: Movies = movies[position]
        holder.bind(movie, listener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(private val itemBinding: RowMoviesBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movie: Movies, listener: (Movies) -> Unit) {
            itemBinding.rowMoviesTextViewName.text = movie.name
            itemBinding.rowMoviesTextViewDuration.text = "${movie.duration} min"
            itemBinding.rowMoviesTextViewCategory.text = movie.category
            itemBinding.rowMoviesTextViewEvaluation.text = movie.evaluation

            itemBinding.root.setOnClickListener {
                listener(movie)
            }
        }
    }

}


