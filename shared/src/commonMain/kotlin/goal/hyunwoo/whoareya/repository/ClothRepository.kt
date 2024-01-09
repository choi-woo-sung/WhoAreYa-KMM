package goal.hyunwoo.whoareya.repository

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import goal.hyunwoo.whoareya.data.remote.ClothRemoteSource
import goal.hyunwoo.whoareya.data.remote.model.ClothingCollectionBox
import io.ktor.util.logging.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ClothRepository(
    private val clothRemoteSource: ClothRemoteSource,
) {
    @NativeCoroutines
    fun getClothingCollectionBoxInfo(): Flow<List<ClothingCollectionBox>> =
        flow {
            clothRemoteSource.getClothingCollectionBoxInfo().onSuccess {
                emit(it.data)
            }.onFailure {
                emit(emptyList())
            }
        }
}
