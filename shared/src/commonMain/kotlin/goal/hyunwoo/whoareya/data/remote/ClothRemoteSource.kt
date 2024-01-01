package goal.hyunwoo.whoareya.data.remote

import goal.hyunwoo.whoareya.data.remote.model.ProductDto
import goal.hyunwoo.whoareya.network.ProductAPI

class ClothRemoteSource(
    private val productAPI: ProductAPI,
) {
    suspend fun getClothingCollectionBoxInfo(): Result<ProductDto> = productAPI.getClothingCollectionBoxInfo()
}
