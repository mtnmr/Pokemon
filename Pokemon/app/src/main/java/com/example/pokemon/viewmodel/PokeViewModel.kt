package com.example.pokemon.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.pokemon.api.IPokeRepository
import com.example.pokemon.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PokeViewModel @Inject constructor(private val repository: IPokeRepository):ViewModel() {

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon:LiveData<Pokemon> = _pokemon

    fun getPoke(id:String){
        viewModelScope.launch {
            try{
                _pokemon.value = repository.getPoke(id)
            }catch (e:Exception){
                Log.d("Poke", e.toString())
            }
        }
    }
}

//class PokeViewModelFactory(private val repository: PokeRepository) : ViewModelProvider.Factory{
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(PokeViewModel::class.java)){
//            @Suppress("UNCHECKED_CAST")
//            return PokeViewModel(repository) as T
//        }
//        throw (IllegalArgumentException("Unknown ViewModel Class"))
//    }
//}