package com.khangnt6090.tvguide.presentation

import com.khangnt6090.tvguide.domain.model.TvShow

data class TvGuideUiState(
    val shows: List<TvShow> = emptyList(),
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null
) {
    val hasCachedData: Boolean
        get() = shows.isNotEmpty()
}
