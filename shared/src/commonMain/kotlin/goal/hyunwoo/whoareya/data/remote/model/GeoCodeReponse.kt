package goal.hyunwoo.whoareya.data.remote.model
import kotlinx.serialization.Serializable
@Serializable
data class GeoCodeReponse(
    val status: String,
    val meta: Meta,
    val addresses: List<Address>,
    val errorMessage: String
)

@Serializable
data class Address(
    val roadAddress: String,
    val jibunAddress: String,
    val englishAddress: String,
    val addressElements: List<AddressElement>,
    val x: String,
    val y: String,
    val distance: Double
)

@Serializable
data class AddressElement(
    val types: List<String>,
    val longName: String,
    val shortName: String,
    val code: String
)

@Serializable
data class Meta(
    val totalCount: Long,
    val page: Long,
    val count: Long
)
