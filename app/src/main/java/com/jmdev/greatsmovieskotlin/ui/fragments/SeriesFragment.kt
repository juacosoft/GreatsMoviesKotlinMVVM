package com.jmdev.greatsmovieskotlin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jmdev.greatsmovieskotlin.databinding.SeriesFragmentBinding
import com.jmdev.greatsmovieskotlin.ui.viewmodels.SeriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SeriesFragment : Fragment() {


    private var _binding: SeriesFragmentBinding?=null
    private val binding get() = _binding!!
    private val seriesViewModel:SeriesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= SeriesFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
}