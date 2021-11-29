package com.jmdev.greatsmovieskotlin.ui.components.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jmdev.greatsmovieskotlin.R
import com.jmdev.greatsmovieskotlin.constants.ServerUrls.IMAGE_PATH
import com.jmdev.greatsmovieskotlin.data.models.MovieModel
import com.jmdev.greatsmovieskotlin.databinding.ItemMovieBinding
import com.jmdev.greatsmovieskotlin.listeners.MovieSelected

class MoviesAdapter (
    private val movieSelected: MovieSelected
        ):RecyclerView.Adapter<MoviesAdapter.BindableViewHolder>() {

    private var movieModelList= mutableListOf<MovieModel>()


    fun setList(_movieList:List<MovieModel>){
        this.movieModelList.clear()
        this.movieModelList=_movieList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder {
        val binding=ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BindableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindableViewHolder, pos: Int) {
        holder.itemView.setOnClickListener{
            movieSelected.onMovieSelected(movieModelList[pos])
        }
        holder.bind(movieModelList[pos])
    }

    override fun getItemCount(): Int = movieModelList.size


    inner class BindableViewHolder(
        private var itemMovieBinding: ItemMovieBinding
    ):RecyclerView.ViewHolder(itemMovieBinding.root){
        fun bind(movieModel: MovieModel){
            Log.d("MoviesAdapter","Bind: ${movieModel.title}")
            itemMovieBinding.itemmovieTitle.text=movieModel.title
            itemMovieBinding.itemmovieRate.rating=(movieModel.vote_average/2).toFloat()
            Glide.with(itemView)
                .load(IMAGE_PATH + movieModel.poster_path)

                .error(R.drawable.ic_placeholder_movie)
                .into(itemMovieBinding.itemmoviePoster)


        }
    }
}