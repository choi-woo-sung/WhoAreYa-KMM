package goal.hyunwoo.whoareya.data.remote

import goal.hyunwoo.whoareya.data.remote.model.GeoCodeReponse
import goal.hyunwoo.whoareya.data.remote.model.ProductDto
import goal.hyunwoo.whoareya.network.NaverAPI
import goal.hyunwoo.whoareya.network.ProductAPI

class MapRemoteSource(
    private val naverAPI: NaverAPI,
) {
    suspend fun fetchGeoCode(query: String): Result<GeoCodeReponse> = naverAPI.fetchGeoCode(query)
}
