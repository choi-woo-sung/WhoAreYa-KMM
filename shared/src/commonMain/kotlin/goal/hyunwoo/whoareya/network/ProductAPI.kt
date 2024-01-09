package goal.hyunwoo.whoareya.network

import goal.hyunwoo.whoareya.data.remote.model.ProductDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders

class ProductAPI(private val ktorApi: KtorApi) : KtorApi by ktorApi {
    suspend fun getClothingCollectionBoxInfo(): Result<ProductDto> =
        runCatching {
            return@runCatching ktorApi.client.get {
                headers {
                    append(HttpHeaders.Authorization, "infuser LL0SSOpdVJrjj/OgFhTFxrEKFzVyJxrCKUyNncefeNLGGocos9LR2BNrSRB5RlWI1F0yqEfIrGMOY26ccNZ7fA==")
                }
                apiUrl("api/15076398/v1/uddi:6dec2a8d-6404-4318-8767-85419b3c45a0"){
                    parameters.append("page", "1")
                    parameters.append("perPage", "10")
                    parameters.append("serviceKey", "LL0SSOpdVJrjj/OgFhTFxrEKFzVyJxrCKUyNncefeNLGGocos9LR2BNrSRB5RlWI1F0yqEfIrGMOY26ccNZ7fA==")
                }
            }.body()
        }
}
