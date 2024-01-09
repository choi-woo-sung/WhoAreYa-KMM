package goal.hyunwoo.whoareya.repository

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import goal.hyunwoo.whoareya.data.remote.ClothRemoteSource
import goal.hyunwoo.whoareya.data.remote.MapRemoteSource
import goal.hyunwoo.whoareya.data.remote.model.ClothingCollectionBox
import goal.hyunwoo.whoareya.data.remote.model.GeoCodeReponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MapRepository(
    private val mapRemoteSource: MapRemoteSource,
) {
    @NativeCoroutines
    fun fetchGeoCode(query: String): Flow<GeoCodeReponse?> =
        flow {
            mapRemoteSource.fetchGeoCode(query).onSuccess {
                emit(it)
            }.onFailure {
                emit(null)
            }
        }
}
