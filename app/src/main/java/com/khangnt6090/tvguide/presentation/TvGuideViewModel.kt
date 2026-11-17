package com.khangnt6090.tvguide.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khangnt6090.tvguide.domain.repository.TvShowRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TvGuideViewModel(
    private val repository: TvShowRepository
) : ViewModel() {
    private val isRefreshing = MutableStateFlow(false)
    private val errorMessage = MutableStateFlow<String?>(null)

    val uiState: StateFlow<TvGuideUiState> = combine(
        repository.observeShows(),
        isRefreshing,
        errorMessage
    ) { shows, refreshing, error ->
        TvGuideUiState(
            shows = shows,
            isRefreshing = refreshing,
            errorMessage = error
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TvGuideUiState()
    )

    init {
        refresh()
    }

    fun refresh() {
        if (isRefreshing.value) return

        viewModelScope.launch {
            isRefreshing.value = true
            errorMessage.value = null

            try {
                repository.refreshShows()
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                errorMessage.value =
                    "Không thể cập nhật dữ liệu. Danh sách đã lưu trên máy vẫn được giữ nguyên."
            } finally {
                isRefreshing.value = false
            }
        }
    }
}
