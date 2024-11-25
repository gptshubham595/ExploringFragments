package com.example.exploringfragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _textData = MutableSharedFlow<String>()
    val textData = _textData as SharedFlow<String>

    fun addText(text: String) {
        viewModelScope.launch {
            _textData.emit(text)
        }
    }
}