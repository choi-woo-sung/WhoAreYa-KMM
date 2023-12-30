package goal.hyunwoo.whoareya.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json

interface KtorApi {
    val client: HttpClient

    fun HttpRequestBuilder.apiUrl(path: String)

    fun HttpRequestBuilder.json()
}

class KtorApiImpl() : KtorApi {
    val prodUrl = "http://infuser.odcloud.kr/oas/docs?namespace=15076398/v1"

    override val client =
        HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }

    override fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(prodUrl)
            encodedPath = path
        }
    }

    override fun HttpRequestBuilder.json() {
        contentType(ContentType.Application.Json)
    }
}
