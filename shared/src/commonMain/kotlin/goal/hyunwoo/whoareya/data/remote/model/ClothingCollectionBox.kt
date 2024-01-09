package goal.hyunwoo.whoareya.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val page: Long,
    val perPage: Long,
    val totalCount: Long,
    val currentCount: Long,
    val matchCount: Long,
    val data: List<ClothingCollectionBox>,
)
@Serializable
data class ClothingCollectionBox(
    @SerialName("의류수거함")
    val name: String,
    @SerialName("위치")
    val location: String,
)
