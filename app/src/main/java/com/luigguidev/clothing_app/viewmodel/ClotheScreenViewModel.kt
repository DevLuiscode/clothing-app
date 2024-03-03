package com.luigguidev.clothing_app.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luigguidev.clothing_app.model.local.model.ClotheModel
import com.luigguidev.clothing_app.model.repositories.ClotheRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClotheScreenViewModel @Inject constructor(
    private val clotheRepository: ClotheRepository
) : ViewModel() {

    var uiState by mutableStateOf(ClotheScreenUiState())
        private set


    private val _initNameClothe = ""
    private val _initDescription = ""
    private val _initPriceHigher = ""
    private val _initPriceUnit = ""
    private val _initImage = ""
    private val _initBtn = false



    private val _name = MutableStateFlow(_initNameClothe)
    val nameClothe = _name.asStateFlow()
    private val _description = MutableStateFlow(_initDescription)
    val description = _description.asStateFlow()
    private val _priceHigher = MutableStateFlow(_initPriceHigher)
    val priceHigher = _priceHigher.asStateFlow()
    private val _priceUnit = MutableStateFlow(_initPriceUnit)
    val priceUnit = _priceUnit.asStateFlow()
    private val _image = MutableStateFlow(_initImage)
    val image = _image.asStateFlow()
    private val _btnAdd = MutableStateFlow(_initBtn)
    val btnAdd = _btnAdd.asStateFlow()

    init {
        viewModelScope.launch {
            clotheRepository.getAllClothe().collect { clotheDataList ->
                uiState = ClotheScreenUiState(
                    clotheList = clotheDataList.map {
                        ClotheItemUiState(
                            uri = Uri.parse(it.imagePath),
                            clotheModel = it
                        )
                    }
                )
            }
        }
    }

    fun setImage(imageUri: String) {
        viewModelScope.launch {
            _image.value = imageUri
        }
    }

    fun insertClothe() {
        viewModelScope.launch {
            val clothe = ClotheModel(
                name = _name.value,
                description = _description.value,
                priceHigher = _priceHigher.value.toDouble(),
                priceUnit = _priceUnit.value.toDouble(),
                imagePath = _image.value
            )

            clotheRepository.insertClothe(clothe)
            clearForm()
        }

        //content://media/picker/0/com.android.providers.media.photopicker/media/1000011153
    }

    fun validateForm(
        name: String,
        description: String,
        priceHigher: String,
        priceUnit: String,
        image: String
    ) {
        _name.value = name
        _description.value = description
        _priceHigher.value = priceHigher
        _priceUnit.value = priceUnit
        _image.value = image
        _btnAdd.value =
            isValidName(name) &&
                    isValidDescription(description) &&
                    isValidPriceHigher(priceHigher) &&
                    isValidPriceUnit(priceUnit) &&
                    isValidImage(image)
    }

    private fun isValidImage(image: String): Boolean = image.isNotEmpty()

    private fun isValidPriceUnit(priceUnit: String): Boolean = priceUnit.isNotEmpty()

    private fun isValidPriceHigher(priceHigher: String): Boolean = priceHigher.isNotEmpty()

    private fun isValidDescription(description: String): Boolean = description.isNotEmpty()

    private fun isValidName(name: String): Boolean = name.isNotEmpty()

    private fun clearForm() {
        _name.value = _initNameClothe
        _description.value = _initDescription
        _priceHigher.value = _initPriceHigher
        _priceUnit.value = _initPriceUnit
        _image.value = _initImage
    }
}