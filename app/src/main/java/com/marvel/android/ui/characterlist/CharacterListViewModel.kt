package com.marvel.android.ui.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.model.CharacterEntity
import com.marvel.android.domain.GetCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterListViewModel(private val getCharactersUseCase: GetCharactersUseCase): ViewModel() {

    private var _characterList = MutableLiveData<MutableList<CharacterEntity>>().apply { value = arrayListOf() }
    val characterList: LiveData<MutableList<CharacterEntity>> = _characterList

    private var _progressLoading = MutableLiveData<Boolean>()
    val progressLoading: LiveData<Boolean> = _progressLoading

    private var _viewEmptyList = MutableLiveData<Boolean>()
    val viewEmptyList: LiveData<Boolean> = _viewEmptyList

    fun getCharactersList(){
        viewModelScope.launch(Dispatchers.IO){
            _progressLoading.postValue(true)
            getCharactersUseCase.execute().let { operationResult ->
                if(operationResult is OperationResult.Success){
                    _characterList.postValue(operationResult.data as MutableList<CharacterEntity>?)
                    _viewEmptyList.postValue(false)
                }else{
                    _viewEmptyList.postValue(true)
                }
            }
            _progressLoading.postValue(false)
        }
    }
}