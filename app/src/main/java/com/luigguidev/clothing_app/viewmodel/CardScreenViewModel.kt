package com.luigguidev.clothing_app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luigguidev.clothing_app.model.local.model.ClotheModel
import com.luigguidev.clothing_app.model.memoryData.model.CardModel
import com.luigguidev.clothing_app.model.repositories.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardScreenViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : ViewModel() {

    private val _initName = ""
    private val _initLastName = ""
    private val _iniPrice = ""

    private val _cardCurrent = MutableStateFlow<List<CardModel>>(emptyList())
    val cardCurrent = _cardCurrent.asStateFlow()

    private val _nameClient = MutableStateFlow(_initName)
    val nameClient = _nameClient.asStateFlow()
    private val _lastNameClient = MutableStateFlow(_initLastName)
    val lastNameClient = _lastNameClient.asStateFlow()
    private val _price = MutableStateFlow(_iniPrice)
    val price = _price.asStateFlow()

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    private fun loadData(){
        viewModelScope.launch {
            _cardCurrent.value = cardRepository.getAll().toMutableList()
        }
    }

    fun addClotheToCard(clotheModel: ClotheModel) {
        viewModelScope.launch {
            cardRepository.addCard(clotheModel)
            loadData()
        }
    }
    fun deleteClothe(cardModel: CardModel){
        viewModelScope.launch {
            cardRepository.delete(cardModel)
            loadData()
        }
    }
}