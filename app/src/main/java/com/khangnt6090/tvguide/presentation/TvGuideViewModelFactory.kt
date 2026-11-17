package com.khangnt6090.tvguide.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.khangnt6090.tvguide.domain.repository.TvShowRepository

class TvGuideViewModelFactory(
    private val repository: TvShowRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvGuideViewModel::class.java)) {
            return TvGuideViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
