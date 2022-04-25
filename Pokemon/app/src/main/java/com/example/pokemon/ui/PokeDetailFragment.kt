package com.example.pokemon.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.pokemon.MyApplication
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentPokeDetailBinding
import com.example.pokemon.viewmodel.PokeViewModel
import com.example.pokemon.viewmodel.PokeViewModelFactory

class PokeDetailFragment : Fragment() {

    private var _binding:FragmentPokeDetailBinding ?= null
    private val binding get() = _binding!!

    private val viewModel:PokeViewModel by activityViewModels{
        PokeViewModelFactory((requireActivity().application as MyApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemon.observe(viewLifecycleOwner){
            binding.pokeNumber.text = it.name
            binding.pokeHeight.text = it.height.toString()
            binding.pokeWeight.text = it.weight.toString()
        }

        binding.searchButton.setOnClickListener {
            val searchId = binding.searchPoke.text.toString()
            viewModel.getPoke(searchId)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}