package goal.hyunwoo.whoareya.network

import goal.hyunwoo.whoareya.data.remote.model.ProductDto
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProductAPI(private val ktorApi: KtorApi) : KtorApi by ktorApi {
    suspend fun getClothingCollectionBoxInfo(): Result<ProductDto> =
        runCatching {
            return@runCatching ktorApi.client.get {
                apiUrl("")
            }.body()
        }
}
