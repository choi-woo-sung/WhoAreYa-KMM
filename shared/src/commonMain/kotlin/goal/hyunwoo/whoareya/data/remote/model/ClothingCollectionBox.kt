package goal.hyunwoo.whoareya.data.remote.model

data class ProductDto(
    val page: Long,
    val perPage: Long,
    val totalCount: Long,
    val currentCount: Long,
    val matchCount: Long,
    val data: List<ClothingCollectionBox>
)

data class ClothingCollectionBox(
    val name: String,
    val location: String
)
