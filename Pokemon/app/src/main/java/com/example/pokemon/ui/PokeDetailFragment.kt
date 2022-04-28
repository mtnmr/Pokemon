package com.example.pokemon.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
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
//            binding.pokeNumber.text = it.toString()
            binding.pokeNumber.text = getString(R.string.poke_number, it.id.toString(), it.name)
            binding.pokeHeight.text = getString(R.string.poke_height, it.getHeight())
            binding.pokeWeight.text = getString(R.string.poke_weight, it.getWeight())
            val imageUrl = it.getImage().toUri().buildUpon().scheme("https").build()
            binding.pokeImage.load(imageUrl)
            binding.pokeType.text = getString(R.string.poke_type, it.getPokeType())

            if (viewModel.pokemon.value?.sprites?.frontFemale != null){
                binding.genderButton.visibility = View.VISIBLE
            }else{
                binding.genderButton.visibility = View.INVISIBLE
            }

            if (viewModel.pokemon.value != null){
                binding.backButton.visibility = View.VISIBLE
                binding.nextButton.visibility = View.VISIBLE
            }
        }

        binding.searchButton.setOnClickListener {
            hideKeyboard(it)
            val searchId = binding.searchPoke.text.toString()
            viewModel.getPoke(searchId)
        }

        binding.backButton.setOnClickListener {
            val id = viewModel.pokemon.value?.id?.minus(1)
            viewModel.getPoke(id.toString())
        }

        binding.nextButton.setOnClickListener {
            val id = viewModel.pokemon.value?.id?.plus(1)
            viewModel.getPoke(id.toString())
        }

        binding.genderButton.setOnClickListener {
            val action = PokeDetailFragmentDirections.actionPokeDetailFragmentToGenderImageFragment()
            findNavController().navigate(action)
        }

        //編集ボタンからフォーカスが外れた時にキーボード非表示
//        binding.searchPoke.setOnFocusChangeListener { view, hasFocus ->
//            if (!hasFocus){
//                val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE)
//                        as InputMethodManager
//                inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
//            }
//        }
    }

    private fun hideKeyboard(view: View){
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken,InputMethodManager.HIDE_NOT_ALWAYS)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}