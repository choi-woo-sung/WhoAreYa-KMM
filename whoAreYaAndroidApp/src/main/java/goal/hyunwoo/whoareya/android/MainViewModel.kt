package goal.hyunwoo.whoareya.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import goal.hyunwoo.whoareya.repository.ClothRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent

class MainViewModel(
    private val repository: ClothRepository
) : ViewModel() {

     val uiState = repository.getClothingCollectionBoxInfo()
        .map { UiState.Success(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), UiState.Loading)
}




sealed class UiState<out T : Any> {
    object Loading : UiState<Nothing>()
    data class Success<out T : Any>(val data: T) : UiState<T>()
    data class Error(val exception: Exception) : UiState<Nothing>()
}
