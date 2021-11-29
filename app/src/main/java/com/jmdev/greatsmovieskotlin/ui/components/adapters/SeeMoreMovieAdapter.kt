package com.jmdev.greatsmovieskotlin.ui.components.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jmdev.greatsmovieskotlin.R
import com.jmdev.greatsmovieskotlin.constants.ServerUrls
import com.jmdev.greatsmovieskotlin.data.models.MovieModel
import com.jmdev.greatsmovieskotlin.databinding.ItemMovieBinding
import com.jmdev.greatsmovieskotlin.databinding.ItemMovieSeemoreBinding
import com.jmdev.greatsmovieskotlin.listeners.MovieSelected

class SeeMoreMovieAdapter (
    private val movieSelected: MovieSelected
        ):RecyclerView.Adapter<SeeMoreMovieAdapter.BindableViewHolder>() {

    private var movieModelList= mutableListOf<MovieModel>()


    fun setList(_movieList:List<MovieModel>){
        this.movieModelList.clear()
        this.movieModelList=_movieList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeMoreMovieAdapter.BindableViewHolder {
        val binding=ItemMovieSeemoreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BindableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeeMoreMovieAdapter.BindableViewHolder, pos: Int) {
        holder.itemView.setOnClickListener{
            movieSelected.onMovieSelected(movieModelList[pos])
        }
        holder.bind(movieModelList[pos])
    }

    override fun getItemCount(): Int = movieModelList.size



    inner class BindableViewHolder(
        private var itemMovieBinding: ItemMovieSeemoreBinding
    ): RecyclerView.ViewHolder(itemMovieBinding.root){
        fun bind(movieModel: MovieModel){
            Log.d("SeeMoreMoviesAdapter","Bind: ${movieModel.title}")
            itemMovieBinding.itemmovieTitle.text=movieModel.title
            itemMovieBinding.itemmovieRate.rating=(movieModel.vote_average/2).toFloat()
            Glide.with(itemView)
                .load(ServerUrls.IMAGE_PATH + movieModel.poster_path)
                .error(R.drawable.ic_placeholder_movie)
                .into(itemMovieBinding.itemmoviePoster)


        }
    }


}