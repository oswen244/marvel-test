package com.marvel.android.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.marvel.android.BuildConfig
import com.marvel.android.base.Constants
import com.marvel.android.base.Extensions.fromJsonString
import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.model.CharacterEntity
import com.marvel.android.data.comics.model.ComicEntity
import com.marvel.android.domain.GetCharacterComicsUseCase
import com.marvel.android.domain.GetCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CharacterViewModel(private val getCharactersUseCase: GetCharactersUseCase,
                         private val getCharacterComicsUseCase: GetCharacterComicsUseCase): ViewModel() {

    private var _characterList = MutableLiveData<MutableList<CharacterEntity>>().apply { value = arrayListOf() }
    val characterList: LiveData<MutableList<CharacterEntity>> = _characterList

    private var _characterComicList = MutableLiveData<MutableList<ComicEntity>>().apply { value = arrayListOf() }
    val characterComicsList: LiveData<MutableList<ComicEntity>> = _characterComicList

    private var _progressLoading = MutableLiveData<Boolean>()
    val progressLoading: LiveData<Boolean> = _progressLoading

    private var _viewEmptyList = MutableLiveData<Boolean>()
    val viewEmptyList: LiveData<Boolean> = _viewEmptyList

    private var _totalCharacters = MutableLiveData<Int>()
    val totalCharacters: LiveData<Int> = _totalCharacters

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getCharactersList(limit: Int?, offset: Int?, isNetWorkAvailable: Boolean){
        viewModelScope.launch(Dispatchers.IO){
            _progressLoading.postValue(true)
            getCharactersUseCase.execute(limit, offset, isNetWorkAvailable).let { operationResult ->
                when (operationResult) {
                    is OperationResult.Success -> {
                        _totalCharacters.postValue(operationResult.data?.data?.total)
                        val characters = Gson().fromJsonString<MutableList<CharacterEntity>>(
                            Gson().toJson(operationResult.data?.data?.results)
                        )
                        if(characters.isNotEmpty()){
                            _characterList.postValue(characters)
                            _viewEmptyList.postValue(false)
                        }else{
                            _viewEmptyList.postValue(true)
                        }
                    }
                    is OperationResult.Error -> {
                        _errorMessage.postValue(operationResult.exception?.message)
                    }
                }
            }
            _progressLoading.postValue(false)
        }
    }

    fun getCharacterComics(id: Int, limit: Int?, offset: Int?, isNetWorkAvailable: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            _progressLoading.postValue(true)
            getCharacterComicsUseCase.execute(id, limit, offset, isNetWorkAvailable).let { operationResult ->
                when(operationResult){
                    is OperationResult.Success -> {
                        val comics = Gson().fromJsonString<MutableList<ComicEntity>>(
                            Gson().toJson(operationResult.data?.data?.results)
                        )
                        if(comics.isNotEmpty()){
                            _characterComicList.postValue(comics)
                            _viewEmptyList.postValue(false)
                        }else{
                            _viewEmptyList.postValue(true)
                        }
                    }
                    is OperationResult.Error -> {
                        _errorMessage.postValue(operationResult.exception?.message)
                    }
                }
            }
            _progressLoading.postValue(false)
        }
    }
}