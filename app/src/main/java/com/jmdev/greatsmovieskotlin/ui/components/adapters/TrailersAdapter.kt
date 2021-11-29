package com.jmdev.greatsmovieskotlin.ui.components.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jmdev.greatsmovieskotlin.constants.ServerUrls.YOUTUBE_PATH
import com.jmdev.greatsmovieskotlin.constants.ServerUrls.YOUTUBE_THUMB
import com.jmdev.greatsmovieskotlin.data.models.TrailerModel
import com.jmdev.greatsmovieskotlin.databinding.ItemMovieBinding
import com.jmdev.greatsmovieskotlin.databinding.ItemTrailerBinding
import com.jmdev.greatsmovieskotlin.listeners.TrailerSelected

class TrailersAdapter (
    private var trailerSelected: TrailerSelected
        ) :RecyclerView.Adapter<TrailersAdapter.BindableHolder>() {
    private var trailersList= mutableListOf<TrailerModel>()

    fun setList(trailers:List<TrailerModel>){
        this.trailersList.clear()
        this.trailersList = trailers.toMutableList()
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableHolder {
        val binding=ItemTrailerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BindableHolder(binding)
    }

    override fun onBindViewHolder(holder: BindableHolder, position: Int) {
        holder.itemView.setOnClickListener{
            trailerSelected.onTRailerSelected(trailersList[position])
        }
        holder.onBind(trailersList[position])
    }

    override fun getItemCount()=trailersList.size

    inner class BindableHolder (
        private var itemMovieBinding: ItemTrailerBinding
    ):RecyclerView.ViewHolder(itemMovieBinding.root){
        fun onBind(trailer:TrailerModel){
            itemMovieBinding.itemmovieName.text=trailer.name
            Glide.with(itemMovieBinding.root)
                .load("$YOUTUBE_THUMB${trailer.key}/maxresdefault.jpg")
                .into(itemMovieBinding.ivItemtrailer)
        }
    }
}