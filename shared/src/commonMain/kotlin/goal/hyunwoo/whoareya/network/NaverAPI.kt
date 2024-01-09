package goal.hyunwoo.whoareya.network

import goal.hyunwoo.whoareya.data.remote.model.ProductDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders

class NaverAPI(private val ktorApi: KtorApi) : KtorApi by ktorApi {
    suspend fun fetchGeoCode(val query : String): Result<ProductDto> =
        runCatching {
            return@runCatching ktorApi.client.get {
                headers {
                    append("X-NCP-APIGW-API-KEY-ID" , "4aqq3u4axp")
                    append("X-NCP-APIGW-API-KEY" , "zMWaY49tYGxxf4Li18ouXRsmFijjQ3iJkt7Zu4w4")
                }
                apiUrl("map-geocode/v2/geocode"){
                    parameters.append("query", query)
                }
            }.body()
        }
}
