package goal.hyunwoo.whoareya.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import goal.hyunwoo.whoareya.repository.ClothRepository
import goal.hyunwoo.whoareya.repository.MapRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val clothRepository: ClothRepository,
    private val mapRepository: MapRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<UiState<List<Pair<String, String>>>>(UiState.Loading)
    val uiState = MutableStateFlow<UiState<List<Pair<String, String>>>>(UiState.Loading)

    @OptIn(ExperimentalCoroutinesApi::class)
    fun setMarker() = viewModelScope.launch {
        uiState.update { UiState.Loading }
        val result = clothRepository.getClothingCollectionBoxInfo().flatMapConcat { list ->
            list.asFlow()
        }.flatMapConcat {
            mapRepository.fetchGeoCode(it.location)
        }.mapNotNull { geoCode ->
            geoCode?.addresses?.firstOrNull()?.let { address ->
                Pair(address.x, address.y)
            }
        }.onEach {
            Log.d("테스트", "setMarker: $it")
        }.toList()
        uiState.update { UiState.Success(result) }
    }
}


sealed class UiState<out T : Any> {
    object Loading : UiState<Nothing>()
    data class Success<out T : Any>(val data: T) : UiState<T>()
    data class Error(val exception: Exception) : UiState<Nothing>()
}
