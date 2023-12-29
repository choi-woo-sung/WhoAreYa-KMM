package goal.hyunwoo.whoareya.network

import goal.hyunwoo.whoareya.data.remote.model.ProductDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class ProductAPI(private val ktorClient: HttpClient) {
    suspend fun getClothingCollectionBoxInfo(productId: Int): Result<ProductDto> =
        runCatching {
            return@runCatching ktorClient.get("http://infuser.odcloud.kr/oas/docs?namespace=15076398/v1").body()
        }
}
