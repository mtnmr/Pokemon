package com.example.pokemon.viewmodel

import androidx.lifecycle.*
import com.example.pokemon.api.PokeRepository
import com.example.pokemon.model.Pokemon
import kotlinx.coroutines.launch
import java.lang.Exception

class PokeViewModel(private val repository: PokeRepository):ViewModel() {

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon:LiveData<Pokemon> = _pokemon

    fun getPoke(id:String){
        viewModelScope.launch {
            try{
                _pokemon.value = repository.getPoke(id)
            }catch (e:Exception){

            }
        }
    }
}

class PokeViewModelFactory(private val repository: PokeRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PokeViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return PokeViewModel(repository) as T
        }
        throw (IllegalArgumentException("Unknown ViewModel Class"))
    }
}