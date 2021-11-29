package com.jmdev.greatsmovieskotlin.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.jmdev.greatsmovieskotlin.R
import com.jmdev.greatsmovieskotlin.data.models.MovieModel
import com.jmdev.greatsmovieskotlin.databinding.SeeMoreBinding
import com.jmdev.greatsmovieskotlin.listeners.MovieSelected
import com.jmdev.greatsmovieskotlin.ui.components.adapters.SeeMoreMovieAdapter
import com.jmdev.greatsmovieskotlin.ui.viewmodels.SeeMoreViewModel
import com.jmdev.greatsmovieskotlin.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeMoreMovieFragment : Fragment(),MovieSelected {

    private var _binding:SeeMoreBinding?=null
    private val binding get() =_binding!!
    private val seeMoreViewModel:SeeMoreViewModel by viewModels()
    lateinit var query:String
    lateinit var option:String
    lateinit var  moviesAdapter:SeeMoreMovieAdapter
    private var page:Int=1
    private var listMovie:MutableList<MovieModel> ?= mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= SeeMoreBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        option= arguments?.getString("option")!!
        moviesAdapter= SeeMoreMovieAdapter(this)
        binding.rvSeemoreMovies.adapter=moviesAdapter
        binding.seemoreBack.setOnClickListener{
            Navigation.findNavController(it).popBackStack()
        }

        callAndRefreshMovies(1)
        binding.refreshMovies.setOnRefreshListener {
            page+=1
            callAndRefreshMovies(page)
        }

        callObservers()

    }
    fun callObservers(){

        seeMoreViewModel.popularList.observe(viewLifecycleOwner, Observer {result->
            result.data?.let {
                listMovie?.addAll(it)
                moviesAdapter.setList(listMovie!!)
            }
            binding.pbSeemoreProgress.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
        })
    }

    override fun onMovieSelected(movieModel: MovieModel) {
        Log.d("MovieSelected","$movieModel")
        val bundle=Bundle()
        bundle.putInt("movieid",movieModel.id)
        view?.let {
            Navigation.findNavController(it).navigate(R.id.detailMovieFragment,bundle)
        }
    }


    fun callAndRefreshMovies(page:Int){

        var title="Search by: "
        when(option){
            "popular"->{

                seeMoreViewModel.fecthAllMovies("popular",page,null)
                title += "Popular"
            }
            "toprated"->{

                seeMoreViewModel.fecthAllMovies("toprated",page,null)
                title += "Top Rated"
            }
            "query"->{
                query=arguments?.getString("querytext")!!
                if(!query.isEmpty()){

                        seeMoreViewModel.fecthAllMovies("query",page,query.toLowerCase())
                    title += query
                }

            }
            else ->{
                Log.e("Error","no Opcion")
                binding.tvSeemoreError.visibility=View.VISIBLE
            }

        }
        title.also { binding.tvSeemoreTitle.text = it }
    }

}