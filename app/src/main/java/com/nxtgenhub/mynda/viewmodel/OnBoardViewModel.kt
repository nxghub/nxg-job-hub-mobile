package com.nxtgenhub.mynda.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nxtgenhub.mynda.data.repositories.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {

    private val _onBoardingComplete = MutableLiveData<Boolean>()
    val onBoardingComplete: LiveData<Boolean> get() = _onBoardingComplete

    init {
        viewModelScope.launch {
            dataStoreRepository.readOnBoardingState().collect { completed ->
                _onBoardingComplete.value = completed
            }
        }
    }

    fun saveOnBoardState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveOnBoardingState(completed)
        }
    }

}