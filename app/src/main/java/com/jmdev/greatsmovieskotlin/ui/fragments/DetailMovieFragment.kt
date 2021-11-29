package com.jmdev.greatsmovieskotlin.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.jmdev.greatsmovieskotlin.R
import com.jmdev.greatsmovieskotlin.constants.ServerUrls.IMAGE_PATH
import com.jmdev.greatsmovieskotlin.data.models.MovieDetailModel
import com.jmdev.greatsmovieskotlin.data.models.MovieModel
import com.jmdev.greatsmovieskotlin.data.models.TrailerModel
import com.jmdev.greatsmovieskotlin.databinding.DetailmovieFragmentBinding
import com.jmdev.greatsmovieskotlin.listeners.TrailerSelected
import com.jmdev.greatsmovieskotlin.ui.components.adapters.TrailersAdapter
import com.jmdev.greatsmovieskotlin.ui.viewmodels.DetailMovieViewModel
import com.jmdev.greatsmovieskotlin.util.NetworkConnection
import com.jmdev.greatsmovieskotlin.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class DetailMovieFragment : Fragment(),TrailerSelected {
    private var _binding: DetailmovieFragmentBinding?=null
    private val binding get() = _binding!!
    private val detailViewmodel:DetailMovieViewModel by viewModels()
    lateinit var trailersAdapter:TrailersAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= DetailmovieFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBackdropMovie.setOnClickListener{
            Navigation.findNavController(it).popBackStack()
        }
        trailersAdapter= TrailersAdapter(this)
        binding.rvDetailmovieTrailers.adapter=trailersAdapter
        Log.d("MovieSelected","${arguments?.getInt("movieid")}")
        val idmovie=arguments?.getInt("movieid")
        if(idmovie!=null){
            callObservers(idmovie)

        }
    }


    fun callObservers(idMovie:Int){
        /*detailViewmodel.isLoading.observe(viewLifecycleOwner, Observer {isLoading->
            if(isLoading){
                binding.pgdetailmovie.visibility=View.VISIBLE
            }else{
                binding.pgdetailmovie.visibility=View.GONE
            }
        })*/

        detailViewmodel.movie(idMovie).observe(viewLifecycleOwner, Observer {result->
            val movieSelected=result.data
            Log.d("DetailMovieFragment","observer: $movieSelected")
            binding.apply {
                Glide.with(binding.root)
                    .load(IMAGE_PATH+movieSelected?.poster_path)
                    .error(R.drawable.ic_placeholder_movie)
                    .into(ivDetailmoviePoster)
                Glide.with(binding.root)
                    .load(IMAGE_PATH+movieSelected?.backdrop_path)
                    .into(ivBackdropMovie)
                tvDetailmovieOverview.text=movieSelected?.overview
                tvDetailmovieTitle.text=movieSelected?.title
                rtDetailmovieRate.rating= (movieSelected?.vote_average?.div(2))?.toFloat() ?: 0.0f

                pgdetailmovie.isVisible = result is Resource.Loading && result.data==null
                errorData.isVisible = result is Resource.Error && result.data==null
                textError.text = result.error?.localizedMessage
            }
        })

        detailViewmodel.isConection.observe(viewLifecycleOwner, Observer {
            if(it){
                detailViewmodel.fechtTrailers()
                binding.rvDetailmovieTrailers.visibility=View.VISIBLE
                binding.tvDetailmovieTrailerstitle.text="Trailers"
            }else{
                binding.rvDetailmovieTrailers.visibility=View.GONE
                binding.tvDetailmovieTrailerstitle.text="No Trailers"
            }
        })

        detailViewmodel.trailers.observe(viewLifecycleOwner, Observer {
            trailersAdapter.setList(it)
        })


    }

    override fun onTRailerSelected(trailerModel: TrailerModel) {
        Log.d("TrailerSelected","$trailerModel")

        if(trailerModel.site.toLowerCase(Locale.ROOT) == "youtube") {
            val bundle=Bundle()
            bundle.putString("urlvideo", trailerModel.key)
            view?.let {
                Navigation.findNavController(it).navigate(R.id.videoPlayerFragment, bundle)
            }
        }else{
            Toast.makeText(context,"no adviable",Toast.LENGTH_SHORT).show()
        }

    }
}